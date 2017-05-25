<template>
  <div id="ConsultEmployeeSign">
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeModal"></success>
    <notice v-if="showError" :infoname="errorMsg" @closeModal="closeModal"></notice>
    <Modal v-model="showHistory" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>签到信息查询</span>
      </p>
    <Date-picker type="daterange" v-model="timeOption" :options="options2" placeholder="选择起始日期"
                 style="width: 200px;float: left"></Date-picker>
    <div style="float: left">
      <Button type="success" @click="checkForHistory()">查询签到信息</Button>
    </div>
    <br><br>
      <Table :data="historyShowList" :columns="historyColumn" :height="523" stripe border></Table>
      <div style="margin: 10px;overflow: scroll">
        <div style="float: right;">
          <Page :total="number" :current="nowPage" @on-change="changePage"></Page>
        </div>
      </div>
      <div slot="footer">
        <Button type="primary" @click="cancel()">确定</Button>
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
        timeOption: [],
        canLogin: false,
        canLogout: false,
        successMsg: '',
        errorMsg: '',
        number: 0,
        nowPage: 1,
        showSuccess: false,
        showError: false,
        showHistory: true,
        historyShowList: [{date: '', signin: '', signout: ''}],
        historyList: [],
        historyColumn: [
          {
            title: '日期',
            key: 'date',
            align: 'center'
          },
          {
            title: '签到时间',
            key: 'signin',
            align: 'center'
          },
          {
            title: '签离时间',
            key: 'signout',
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
        }
      }
    },
    watch: {
      '$route': 'CheckSignState'
    },
    components: {
      success,
      notice
    },
    props: ['id'],
    methods: {
      checkForHistory () {
        this.$http({
          url: 'http://localhost:8081/consultLogData',
          method: 'POST',
          params: {
            id: this.id,
            st: this.timeOption[0].toLocaleDateString(),
            et: this.timeOption[1].toLocaleDateString()
          }
        }).then((response) => {
          if (response.body.status) {
            this.historyList = response.body.data
            this.number = this.historyList.length
            this.dealWithDate()
            if (this.number !== 0) {
              this.historyShowList = this.getThisPage(0)
            } else {
              this.errorMsg = '该员工暂时没有签到记录'
              this.showError = true
            }
          } else {
            this.errorMsg = '无法获取签到数据'
            this.showError = true
          }
        }, (response) => {
          this.errorMsg = '无法获取签到数据，请检查您的网络连接'
          this.showError = true
        })
      },
      dealWithDate () {
        this.historyList.forEach(function (item) {
          item.date = new Date(item.date).toLocaleDateString()
        })
      },
      getThisPage (startData) {
        return this.historyList.slice(startData * 10, (startData + 1) * 10)
      },
      changePage () {
        this.historyShowList = this.getThisPage(this.nowPage - 1)
      },
      closeModal () {
        this.showSuccess = false
        this.showError = false
      },
      cancel () {
        this.$emit('closeModal')
      }
    }
  }
</script>
