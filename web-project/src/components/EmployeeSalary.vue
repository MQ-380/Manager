<template>
  <div id="consultSalary">
    <notice v-if="showError" :infoname="errorMsg" @closeModal="closeError"></notice>
    <punish v-if="showPunish" :detail="nowPunishDetail" @closeModal="closePunish"></punish>
    <template>
      <Date-picker type="month" v-model="startMonth" placeholder="选择开始月份" style="width: 200px"></Date-picker>
      <br><br>
      <Date-picker type="month" v-model="endMonth" placeholder="选择结束月份" style="width: 200px"></Date-picker>
      <br><br>
      <Button style="float: left" type="success" @click="consultSalary">查询工资</Button>
      <br><br>
      <div v-if="showSalary">
        <Table :data="salaryShowList" :columns="columns" :height="523" stripe border></Table>
        <div style="margin: 10px;overflow: scroll">
          <div style="float: right;">
            <Page :total="number" :current="nowPage" @on-change="changePage"></Page>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>


<script>
  import notice from './Notice'
  import punish from './EmployeePunishDetail'

  export default {
    data () {
      return {
        salaryList: [],
        salaryShowList: [],
        columns: [
          {
            title: '工资月份',
            key: 'date',
            align: 'center'
          },
          {
            title: '总工资',
            key: 'total',
            align: 'center'
          },
          {
            title: '基本工资',
            key: 'fundamental',
            align: 'center'
          },
          {
            title: '奖励',
            key: 'reward',
            align: 'center'
          },
          {
            title: '罚金',
            key: 'punish',
            align: 'center'
          },
          {
            title: '查看奖惩详情',
            key: 'action',
            align: 'center',
            width: 150,
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.nowPunishDetail = this.salaryShowList[params.index].rePunSalary
                      this.showPunish = true
                    }
                  }
                }, '查看')
              ])
            }
          }
        ],
        nowPage: 1,
        number: 0,
        nowPunishDetail: [],
        startMonth: '',
        endMonth: '',
        showError: false,
        showSalary: false,
        showPunish: false,
        errorMsg: ''
      }
    },
    methods: {
      consultSalary () {
        if (this.isDateRight()) {
          this.$store.commit('GETLOGIN')
          this.$http({
            url: 'http://localhost:8081/consultSalary',
            method: 'POST',
            params: {
              id: this.$store.state.LoginState.loginId,
              st: this.dealWithMonth(this.startMonth),
              et: this.dealWithMonth(this.endMonth)
            }
          }).then((response) => {
            if (response.body.status) {
              this.salaryList = response.body.totalSalary
              this.dealSalary(this.dealWithMonth, response.body.fundamental)
              this.getThisPageData(0)
              this.showSalary = true
            } else {
              this.errorMsg = '暂时无法查询工资，请稍后重试'
              this.showError = true
            }
          }, (response) => {
            this.errorMsg = '无法查询工资，请检查网络连接'
            this.showError = true
          })
        } else {
          this.$Message.error('请检查日期选择，开始日期应早于结束日期')
        }
      },
      isDateRight () {
        return this.startMonth <= this.endMonth
      },
      dealSalary (dealDate, basic) {
        this.salaryList.forEach(function (item) {
          item.date = dealDate(item.date)
          item.punish = -item.punish
          item.fundamental = basic
        })
      },
      dealWithMonth (date) {
        let dateTmp = new Date(date)
        return dateTmp.getFullYear() + '-' + (dateTmp.getMonth() + 1)
      },
      getThisPageData (page) {
        this.salaryShowList = this.salaryList.slice(page, (page + 1) * 10)
      },
      changePage () {
        this.getThisPageData(this.nowPage - 1)
      },
      closeError () {
        this.showError = false
      },
      closePunish () {
        this.showPunish = false
      }
    },
    components: {
      notice,
      punish
    }
  }
</script>
