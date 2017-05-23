<template>
  <div id="EmployeeSign">
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeModal"></success>
    <notice v-if="showError" :infoname="errorMsg" @cancel="closeModal"></notice>
    <br><br>
    <div style="float: left">
      <Button v-if="canLogin" type="success" size="large" @click="EmployeeSignIn">
        <span>签到</span>
      </Button>
      <Button v-else disabled size="large">签到</Button>
      <Button v-if="canLogout" type="error" size="large" @click="EmployeeSignOut">
        <span>签离</span>
      </Button>
      <Button v-else disabled size="large">签离</Button>
    </div>
    <br><br>
    <br>
    <Date-picker type="daterange" v-model="timeOption" :options="options2" placeholder="选择起始日期"
                 style="width: 200px;float: left"></Date-picker>
    <div style="float: left">
      <Button type="success" @click="checkForHistory()">查询签到信息</Button>
    </div>
    <br><br>
    <div v-if="showHistory">
      <Table :data="historyShowList" :columns="historyColumn" :height="523" stripe border></Table>
      <div style="margin: 10px;overflow: scroll">
        <div style="float: right;">
          <Page :total="number" :current="nowPage" @on-change="changePage"></Page>
        </div>
      </div>
    </div>
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
        historyShowList: [
          {
            date: '2017-05-21',
            signin: '23:45:01',
            signout: '23:56:01'
          }
        ],
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
    created () {
      this.CheckSignState()
    },
    watch: {
      '$route': 'CheckSignState'
    },
    components: {
      success,
      notice
    },
    methods: {
      CheckSignState () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: 'http://localhost:8081/isLogin',
          method: 'POST',
          params: {
            id: id
          }
        }).then((response) => {
          if (response.body.status) {
            this.canLogin = response.body.in
            if (!this.canLogin) {
              this.$http({
                url: 'http://localhost:8081/isLogout?staff.staid' + id,
                method: 'POST'
              }).then((response) => {
                if (response.body.status) {
                  this.canLogout = response.body.out
                }
              }, (response) => {
                this.$Message.error('请检查网络连接')
              })
            }
          }
        }, (response) => {
          this.$Message.error('请检查网络连接')
        })
      },
      EmployeeSignIn () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: 'http://localhost:8081/login',
          method: 'POST',
          params: {
            id: id
          }
        }).then((response) => {
          if (response.body.status) {
            this.successMsg = '签到成功'
            this.showSuccess = true
            this.canLogin = false
            this.canLogout = true
          } else {
            this.errorMsg = '签到失败，请重试'
            this.showError = true
          }
        }, (response) => {
          this.errorMsg = '签到失败，请检查您的网络连接'
          this.showError = true
        })
      },
      EmployeeSignOut () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: 'http://localhost:8081/Logout',
          method: 'POST',
          params: {
            id: id
          }
        }).then((response) => {
          if (response.body.status) {
            this.successMsg = '签离成功'
            this.showSuccess = true
            this.canLogin = false
            this.canLogout = false
          } else {
            this.errorMsg = '签离失败，请检查您的网络连接'
            this.showError = true
          }
        })
      },
      checkForHistory () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: 'http://localhost:8081/consultLogData',
          method: 'POST',
          params: {
            id: id,
            st: this.timeOption[0],
            et: this.timeOption[1]
          }
        }).then((response) => {
          if (response.body.status) {
            this.historyList = response.body.data
          } else {
            this.errorMsg = '无法获取签到数据'
            this.showError = true
          }
        }, (response) => {
          this.errorMsg = '无法获取签到数据，请检查您的网络连接'
          this.showError = true
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
      }
    }
  }
</script>
