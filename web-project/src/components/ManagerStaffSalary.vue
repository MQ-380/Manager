<template>
  <div id="consultSalary">
    <notice v-if="showError" :infoname="errorMsg" @closeModal="closeError"></notice>
    <punish v-if="showPunish" :detail="nowPunishDetail" @closeModal="closePunish"></punish>
    <addPunish v-if="showAdd" :detail="nowAllStaff" :time="Date.now()"  @closeModal="closeAdd"></addPunish>
    <Button type="success" @click="newAdd" style="float: left">添加业绩奖惩</Button>
    <br><br>
    <template>
      <label class="left">请选择查询的员工</label>
      <br><br>
      <Select v-model="name" style="width:200px; float: left;">
        <Option v-for="item in nowAllStaff" :value="item.staid" :key="item">{{item.name}}</Option>
      </Select>
      <br><br>
      <Date-picker type="month" v-model="startMonth" placeholder="选择开始月份" style="width: 200px"></Date-picker>
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

<style>
  .punish {
    color: #ac2925;
  }
  .reward {
    color: #5cb85c;
  }
</style>

<script>
  import notice from './Notice'
  import punish from './EmployeePunishDetail'
  import addPunish from './ManagerAddPunish'

  export default {
    data () {
      return {
        salaryList: [],
        salaryShowList: [],
        nowAllStaff: [],
        name: '',
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
                      if (this.checkForThisMonth(this.salaryShowList[params.index])) {
                        this.showPunish = true
                      } else {
                        this.errorMsg = '本月没有奖惩信息'
                        this.showError = true
                      }
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
        staffInfo: {},
        allPunish: [],
        startMonth: '',
        endMonth: '',
        showAdd: false,
        showError: false,
        showSalary: false,
        showPunish: false,
        errorMsg: ''
      }
    },
    created () {
      this.getName()
    },
    methods: {
      getName () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: 'http://localhost:8081/consultPersonalInformation?id=' + id,
          method: 'POST'
        }).then((response) => {
          this.staffInfo = response.body.staff
          let depart = this.staffInfo.deid
          let toAdd = []
          this.$http({
            url: 'http://localhost:8081/consultAllStaff',
            method: 'POST'
          }).then((response) => {
            response.body.staff.forEach(function (item) {
              if (item.deid === depart) {
                toAdd.push(item)
              }
            })
          })
          this.nowAllStaff = toAdd
        })
      },
      newAdd () {
        this.showAdd = true
      },
      consultSalary () {
        if (this.isDateRight()) {
          this.$http({
            url: 'http://localhost:8081/consultSalary',
            method: 'POST',
            params: {
              id: this.name,
              st: this.dealWithMonth(this.startMonth),
              et: this.dealWithMonth(this.endMonth)
            }
          }).then((response) => {
            if (response.body.status) {
              this.salaryList = response.body.totalSalary
              this.allPunish = response.body.rePunSalary
              this.number = this.salaryList.length
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
      checkForThisMonth (nowInfo) {
        let thisMonth = new Date(nowInfo.date).getMonth() - 1
        this.allPunish.forEach((item) => {
          if (new Date(item.date).getMonth() === thisMonth) {
            this.nowPunishDetail.push(item)
          }
        })
        return this.nowPunishDetail.length !== 0
      },
      isDateRight () {
        return this.startMonth <= this.endMonth
      },
      dealSalary (dealDate, basic) {
        this.salaryList.forEach(function (item) {
          item.date = dealDate(item.date)
          item.punish = -item.punish
          item.fundamental = basic
          item.cellClassName = {
            punish: 'punish',
            reward: 'reward'
          }
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
        this.nowPunishDetail = []
      },
      closeAdd () {
        this.showAdd = false
      }
    },
    components: {
      notice,
      punish,
      addPunish
    }
  }
</script>
