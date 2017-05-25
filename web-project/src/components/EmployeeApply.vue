<template>
  <div id="apply">
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeRepealSuccess"></success>
    <notice v-if="showError" :infoname="errorMsg" @closeModal="cancel"></notice>
    <apply v-if="showApply" @closeSuccess="closeSuccess" @cancel="cancel"></apply>
    <Button type="success" @click="newApply" style="float: left">新建外出申请</Button>
    <br><br>
    <Select v-model="consultType" style="width: 200px;float: left">
      <Option value="1">根据开始时间查询</Option>
      <Option value="2">根据结束时间查询</Option>
    </Select>
    <Date-picker type="daterange" :options="options2" v-model="searchTime" placeholder="选择起始日期"
                 style="width: 200px;float: left"></Date-picker>
    <Button style="float: left" type="success" @click="consultApplyData">查询所有外出申请</Button>
    <br><br>
    <div v-if="showHistory">
      <Table :data="historyShowList" :columns="columns" :height="523" stripe border></Table>
      <div style="margin: 10px;overflow: scroll">
        <div style="float: right;">
          <Page :total="number" :current="nowPage" @on-change="changePage"></Page>
        </div>
      </div>
    </div>

    <Modal v-model="showRepeal" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>撤销外出申请确认</span>
      </p>
      <div style="text-align:center">
        <span>您是否确定要撤销编号为{{nowId}}，时间从{{nowStart}}到{{nowEnd}}的外出申请？</span>
      </div>
      <div slot="footer">
        <Button type="primary" @click="confirmToRepeal()">确定</Button>
        <Button type="ghost" @click="cancelToRepeal">取消</Button>
      </div>
    </Modal>

    <Modal v-model="showDetail" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>外出事由</span>
      </p>
      <div style="text-align:left">
        <span>{{nowDetail}}</span>
      </div>
      <div slot="footer">
        <Button type="primary" @click="cancel">确定</Button>
      </div>
    </Modal>


  </div>
</template>

<script>
  import apply from './EmployeeNewApply'
  import success from './Success'
  import notice from './Notice'

  export default {
    data () {
      return {
        consultType: '1',
        nowPage: 1,
        number: 0,
        nowId: '',
        nowStart: '',
        nowEnd: '',
        nowDetail: '',
        searchTime: [],
        historyShowList: [],
        historyList: [],
        columns: [
          {
            title: '操作',
            key: 'action',
            width: 100,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small',
                    disabled: this.historyShowList[params.index].isconfirm !== 0
                  },
                  on: {
                    click: () => {
                      this.nowId = this.historyShowList[params.index].leaveid
                      this.nowStart = this.historyShowList[params.index].stime
                      this.nowEnd = this.historyShowList[params.index].etime
                      this.showRepeal = true
                    }
                  }
                }, '撤销')
              ])
            }
          },
          {
            title: '状态',
            key: 'confirm',
            width: 150,
            align: 'center'
          },
          {
            title: '申请单编号',
            key: 'leaveid',
            align: 'center'
          },
          {
            title: '开始时间',
            key: 'stime',
            align: 'center'
          },
          {
            title: '结束时间',
            key: 'etime',
            align: 'center'
          },
          {
            title: '类型',
            key: 'type',
            align: 'center'
          },
          {
            title: '具体事由',
            key: 'description',
            width: 100,
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.nowDetail = (this.historyShowList[params.index].description === undefined || this.historyShowList[params.index].description === '') ? '没有填写具体事由' : this.historyShowList[params.index].description
                      this.showDetail = true
                    }
                  }
                }, '查看事由')
              ])
            }
          },
          {
            title: '操作人',
            key: 'operator',
            align: 'center'
          }
        ],
        options2: {
          shortcuts: [
            {
              text: '最近一周',
              value () {
                const end = new Date()
                const start = new Date()
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
                return [start, end]
              }
            },
            {
              text: '最近一个月',
              value () {
                const end = new Date()
                const start = new Date()
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
                return [start, end]
              }
            },
            {
              text: '最近三个月',
              value () {
                const end = new Date()
                const start = new Date()
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
                return [start, end]
              }
            }
          ]
        },
        successMsg: '',
        errorMsg: '',
        showHistory: false,
        showApply: false,
        showRepeal: false,
        showDetail: false,
        showSuccess: false,
        showError: false
      }
    },
    methods: {
      newApply () {
        this.showApply = true
      },
      confirmToRepeal () {
        this.$store.commit('GETLOGIN')
        this.$http({
          url: 'http://localhost:8081/deleteApply',
          method: 'POST',
          params: {
            leaveid: this.nowId
          }
        }).then((response) => {
          if (response.body.status) {
            this.successMsg = '成功撤销申请'
            this.showSuccess = true
          } else {
            this.errorMsg = '无法撤销申请，请稍后重试'
            this.showError = true
          }
        }, (response) => {
          this.errorMsg = '无法撤销申请，请检查网络连接'
          this.showError = true
        })
      },
      cancelToRepeal () {
        this.showRepeal = false
      },
      consultApplyData () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: this.consultType === '1' ? 'http://localhost:8081/consultPersonalAllApplyByST' : 'http://localhost:8081/consultPersonalAllApplyByET',
          method: 'POST',
          params: {
            id: id,
            stime: this.searchTime[0].toLocaleDateString(),
            etime: this.searchTime[1].toLocaleDateString()
          }
        }).then((response) => {
          if (response.body.status) {
            this.historyList = response.body.data
            this.number = this.historyList.length
            this.dealTheData()
            this.getThisPageData(0)
            this.showHistory = true
          } else {
            this.errorMsg = '无法获取所有外出申请，请稍后重试'
            this.showError = true
          }
        }, (response) => {
          this.errorMsg = '无法获取所有外出申请，请检查网络连接'
          this.showError = true
        })
      },
      dealTheData () {
        let http = this.$http
        this.historyList.forEach(function (item) {
          http({
            url: 'http://localhost:8081/consultNameByStaid',
            method: 'POST',
            params: {
              staid: item.operator
            }
          }).then((response) => {
            item.operator = response.body.name
            item.confirm = item.isconfirm === 0 ? '待确认' : '已确认'
            item.type = item.type === 1 ? '事假' : '公事外出'
            item.stime = new Date(item.stime).toLocaleDateString()
            item.etime = new Date(item.etime).toLocaleDateString()
          })
        })
      },
      getThisPageData (page) {
        this.historyShowList = this.historyList.slice(page, (page + 1) * 10)
      },
      changePage () {
        this.getThisPageData(this.nowPage - 1)
      },
      closeSuccess () {
        this.showApply = false
        this.consultApplyData()
      },
      closeRepealSuccess () {
        this.showSuccess = false
        this.consultApplyData()
        this.showRepeal = false
      },
      cancel () {
        this.showApply = false
        this.showError = false
        this.showDetail = false
      }
    },
    components: {
      apply,
      success,
      notice
    }

  }
</script>
