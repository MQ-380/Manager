<template>
  <div id="apply">
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeConfirmSuccess"></success>
    <notice v-if="showError" :infoname="errorMsg" @closeModal="cancel"></notice>
    <Button type="success" @click="consultApplyData()" style="float: left">查询全部外出申请</Button>
    <br><br>
    <div v-if="showHistory">
    <Table :data="historyShowList" :columns="columns" :height="523" stripe border></Table>
    <div style="margin: 10px;overflow: scroll">
      <div style="float: right;">
        <Page :total="number" :current="nowPage" @on-change="changePage"></Page>
      </div>
    </div>
    </div>
    <Modal v-model="showConfirm" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>外出申请确认</span>
      </p>
      <div style="text-align:center">
        <span>您将要确认编号为{{nowId}}，时间从{{nowStart}}到{{nowEnd}}的外出申请？</span>
      </div>
      <div slot="footer">
        <Button type="primary" @click="confirm()">确定</Button>
        <Button type="ghost" @click="cancel()">取消</Button>
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
        staffInfo: {},
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
                      this.showConfirm = true
                    }
                  }
                }, '确认')
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
            title: '员工编号',
            key: 'staid',
            align: 'center'
          },
          {
            title: '姓名',
            key: 'name',
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
          }
        ],
        successMsg: '',
        errorMsg: '',
        showHistory: false,
        showConfirm: false,
        showDetail: false,
        showSuccess: false,
        showError: false
      }
    },
    created () {
      this.getStaffInformation()
    },
    watch: {
      'route': 'consultApplyData'
    },
    methods: {
      newApply () {
        this.showApply = true
      },
      confirm () {
        this.$store.commit('GETLOGIN')
        this.$http({
          url: 'http://localhost:8081/confirmApply',
          method: 'POST',
          params: {
            id: this.$store.state.LoginState.loginId,
            leaveid: this.nowId
          }
        }).then((response) => {
          if (response.body.status) {
            this.successMsg = '成功确认申请'
            this.showSuccess = true
          } else {
            this.errorMsg = '无法确认申请，请稍后重试'
            this.showError = true
          }
        }, (response) => {
          this.errorMsg = '无法确认申请，请检查网络连接'
          this.showError = true
        })
      },
      consultApplyData () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: 'http://localhost:8081/consultAllApply',
          method: 'POST',
          params: {
            id: id,
            deid: this.staffInfo.deid,
            rank: this.staffInfo.rank
          }
        }).then((response) => {
          if (response.body.status) {
            this.dealTheData(response.body.data)
            this.historyList = response.body.data
            this.number = this.historyList.length
            this.getThisPageData(0)
            this.showHistory = true
            if (this.number === 0) {
              this.errorMsg = '本部门暂无外出申请'
              this.showError = true
            }
          } else {
            this.errorMsg = '无法获取所有外出申请，请稍后重试'
            this.showError = false
          }
        }, (response) => {
          this.errorMsg = '无法获取所有外出申请，请检查网络连接'
          this.showError = false
        })
      },
      dealTheData (list) {
        let http = this.$http
        list.forEach(function (item) {
          http({
            url: 'http://localhost:8081/consultNameByStaid',
            method: 'POST',
            params: {
              staid: item.staid
            }
          }).then((response) => {
            item.name = response.body.name
            item.confirm = item.isconfirm === 0 ? '待确认' : '已确认'
            item.type = item.type === 1 ? '事假' : '公事外出'
            item.stime = new Date(item.stime).toLocaleDateString()
            item.etime = new Date(item.etime).toLocaleDateString()
          })
        })
      },
      getStaffInformation () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: 'http://localhost:8081/consultPersonalInformation?id=' + id,
          method: 'POST'
        }).then((response) => {
          this.staffInfo = response.body.staff
        })
      },
      getThisPageData (page) {
        this.historyShowList = this.historyList.slice(page, (page + 1) * 10)
      },
      changePage () {
        this.getThisPageData(this.nowPage - 1)
      },
      closeConfirmSuccess () {
        this.showSuccess = false
        this.consultApplyData()
        this.showConfirm = false
      },
      cancel () {
        this.showApply = false
        this.showError = false
        this.showDetail = false
        this.showConfirm = false
      }
    },
    components: {
      success,
      notice
    }

  }
</script>
