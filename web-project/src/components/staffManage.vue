<template>
  <div id="staff">
    <notice v-if="showNotice" :infoname="noticeMsg" @closeModal="closeModal"></notice>
    <add v-if="showAdd" @closeModal="closeAndFresh" @cancel="closeModal"></add>
    <delStaff v-if="showDelete" :deleteItem="deleteItem" @closeModal="closeAndFresh" @cancel="closeModal"></delStaff>
    <edit v-if="showEdit" :editItem="staffInfo" @closeModal="closeAndFresh" @cancel="closeModal"></edit>
    <label class="left">请选择部门</label>
    <br><br>
    <Select v-model="department.selectDepartment" style="width:200px; float: left;">
      <Option v-for="item in departmentList" :value="item.deid">{{item.name}}</Option>
    </Select>
    <div class="left">
    <Button type="primary" @click="getStaff()">获得员工列表</Button>
    <Button type="ghost" @click="getDepartmentList()">刷新部门列表</Button>
    </div>
    <br><br>
    <div v-if="showStaff">
      <div class='left'>
        <Button type="primary" @click="toAdd">新增员工</Button>
        <Button type="error" @click="toDelete">删除员工</Button>
      </div>
      <div style="float: right">
        <Button type="primary" shape="circle" @click="getStaff()" icon="ios-refresh"></Button>
      </div>
      <br><br>
      <Table :data="staffShowList" :columns="staffColumn" :height="523" @on-select="addDeleteItem" @on-select-all="addDeleteItem"
             stripe border></Table>
      <div style="margin: 10px;overflow: scroll">
        <div style="float: right;">
          <Page :total="number" :current="nowPage" @on-change="changePage"></Page>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
  .left {
    float: left;
  }
</style>

<script>
  import notice from './Notice'
  import add from './StaffAdd'
  import delStaff from './StaffDelete'
  import edit from './StaffEdit'

  export default {
    data () {
      return {
        department: {
          selectDepartment: ''
        },
        departmentList: this.getDepartmentList(),
        staffColumn: [
          {
            type: 'selection',
            width: 60,
            align: 'center'
          },
          {
            title: '员工编号',
            key: 'ID',
            align: 'center',
            width: 100
          },
          {
            title: '员工姓名',
            key: 'name',
            align: 'center'
          },
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
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.staffInfo = this.staffShowList[params.index]
                      this.showEdit = true
                    }
                  }
                }, '更新信息')
              ])
            }
          }
        ],
        staffList: [],
        staffShowList: [],
        deleteItem: [],
        noticeMsg: '',
        nowPage: 1,
        number: 0,
        staffInfo: {},
        showNotice: false,
        showStaff: true,
        showAdd: false,
        showDelete: false,
        showEdit: false,
        inSearch: false,
        searchResult: []
      }
    },
    components: {
      notice,
      add,
      delStaff,
      edit
    },
    methods: {
      getDepartmentList () {
        this.$http.post('http://localhost:8081/consultDepartment.action')
            .then((response) => {
              this.$store.commit('SETDEPARTDATA', response.body.department)
              this.departmentList = response.body.department
            },
            (response) => {
              this.noticeMsg = '无法获取当前的部门列表，请检查您的网络连接'
              this.showNotice = true
            })
      },
      getStaff () {
        this.showStaff = true
        this.$http({
          url: 'http://localhost:8081/consultAllStaff',
          method: 'POST',
          params: {
            departmentId: this.department.selectDepartment
          }
        }).then((response) => {
          if (response.body.status) {
            this.staffList = response.body.staff
          } else {
            this.noticeMsg = '无法获得该部门的员工列表，请刷新部门列表后重新查询。'
            this.showNotice = true
          }
        }, (response) => {
          this.noticeMsg = '无法查询用户列表，请检查网络连接。'
          this.showNotice = true
        })
        this.staffShowList = this.getThisPageData(0)
        this.number = this.getStaffSize()
        this.nowPage = 1
      },
      getStaffSize () {
        return this.staffList.length
      },
      addDeleteItem (selected) {
        let items = []
        selected.forEach(function (item) {
          item.push(item)
        })
        this.deleteItem = items
      },
      getThisPageData (startData) {
        if (this.inSearch) {
          return this.searchResult.slice(startData, startData + 10)
        } else {
          return this.staffList.slice(startData, startData + 10)
        }
      },
      changePage () {
        this.staffShowList = this.getThisPageData(this.nowPage)
      },
      toAdd () {
        this.showAdd = true
      },
      toDelete () {
        this.showDelete = true
      },
      closeAndFresh () {
        this.$http({
          url: 'http://localhost:8081/consultAllStaff',
          method: 'POST',
          params: {
            departmentId: this.department.selectDepartment
          }
        }).then((response) => {
          if (response.body.status) {
            this.staffList = response.body.staff
            this.staffShowList = this.getThisPageData(this.nowPage)
          } else {
            this.noticeMsg = '无法获得该部门的员工列表，请刷新部门列表后重新查询。'
            this.showNotice = true
          }
        }, (response) => {
          this.noticeMsg = '无法查询用户列表，请检查网络连接。'
          this.showNotice = true
        })
      },
      closeModal () {
        this.showNotice = false
        this.showDelete = false
        this.showEdit = false
        this.showAdd = false
      }
    }
  }
</script>
