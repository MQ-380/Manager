<template>
  <div id="staff">
    <notice v-if="showNotice" :infoname="noticeMsg" @closeModal="closeModal"></notice>
    <add v-if="showAdd" :Info="staffInfo" @closeModal="closeAndFresh" @cancel="closeModal"></add>
    <delStaff v-if="showDelete" :deleteItem="deleteItem" @closeModal="closeAndFresh" @cancel="closeModal"></delStaff>
    <label class="left">请选择部门</label>
    <br><br>
    <Select v-model="department.selectDepartment" style="width:200px; float: left;">
      <Option v-for="item in departmentList" :value="item.deid" :key="item">{{item.name}}</Option>
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
      <Input v-model="searchKeyword"  :style="searchWidth" :icon="iconType" class="freshButton" @on-focus="startSearch()"
             @on-click="endSearch()" @on-change="startSearch()" :placeholder="holder"/>
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

  .freshButton {
    float: right
  }
</style>

<script>
  import notice from './Notice'
  import add from './StaffAddAndEdit'
  import delStaff from './StaffDelete'

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
            key: 'staid',
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
                      this.staffInfo.departmentname = this.getDepartmentName()
                      this.staffInfo.time = new Date(this.staffInfo.time)
                      this.staffInfo.headname = '修改员工信息'
                      this.staffInfo.type = 'edit'
                      this.showAdd = true
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
        showStaff: false,
        showAdd: false,
        showDelete: false,
        showEdit: false,
        inSearch: false,
        searchResult: [],
        searchWidth: 'width: 50px',
        iconType: 'ios-search-strong',
        holder: ''
      }
    },
    components: {
      notice,
      add,
      delStaff
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
        this.$http({
          url: 'http://localhost:8081/consultAllStaff',
          method: 'POST',
          params: {
            departmentId: this.department.selectDepartment
          }
        }).then((response) => {
          if (response.body.status) {
            this.staffList = this.staffInSelected(response.body.staff, this.department.selectDepartment)
            this.staffShowList = this.getThisPageData(0)
            this.number = this.getStaffSize()
            this.nowPage = 1
            this.showStaff = true
          } else {
            this.noticeMsg = '无法获得该部门的员工列表，请刷新部门列表后重新查询。'
            this.showNotice = true
          }
        }, (response) => {
          this.noticeMsg = '无法查询用户列表，请检查网络连接。'
          this.showNotice = true
        })
      },
      staffInSelected (allStaff, selectedDepartment) {
        let staffInSelectedDepartment = []
        allStaff.forEach(function (item) {
          if (item.deid === selectedDepartment) {
            staffInSelectedDepartment.push(item)
          }
        })
        return staffInSelectedDepartment
      },
      getStaffSize () {
        return this.staffList.length
      },
      addDeleteItem (selected) {
        let items = []
        selected.forEach(function (item) {
          items.push(item)
        })
        this.deleteItem = items
      },
      getThisPageData (startData) {
        if (this.inSearch) {
          return this.searchResult.slice(startData * 10, (startData + 1) * 10)
        } else {
          return this.staffList.slice(startData * 10, (startData + 1) * 10)
        }
      },
      changePage () {
        this.staffShowList = this.getThisPageData(this.nowPage - 1)
      },
      getDepartmentName () {
        let nowSelected = this.department.selectDepartment
        let selectedDepartment
        this.departmentList.forEach(function (item) {
          if (item.deid === nowSelected) {
            selectedDepartment = item
          }
        })
        return selectedDepartment.name
      },
      toAdd () {
        this.staffInfo = {
          headname: '新增员工',
          name: '',
          sex: '',
          email: '',
          phone: '',
          time: '',
          rank: '',
          deid: this.department.selectDepartment,
          departmentname: this.getDepartmentName(),
          training: '',
          skill: '',
          type: 'add'
        }
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
            this.staffList = this.staffInSelected(response.body.staff, this.department.selectDepartment)
            this.staffShowList = this.getThisPageData(this.nowPage)
            this.number = this.getStaffSize()
            this.nowPage = 1
            this.showStaff = true
          } else {
            this.noticeMsg = '无法获得该部门的员工列表，请刷新部门列表后重新查询。'
            this.showNotice = true
          }
        }, (response) => {
          this.noticeMsg = '无法查询用户列表，请检查网络连接。'
          this.showNotice = true
        })
        this.closeModal()
        this.getStaff()
        this.deleteItem = []
      },
      closeModal () {
        this.showNotice = false
        this.showDelete = false
        this.showEdit = false
        this.showAdd = false
      },
      startSearch () {
        this.searchWidth = 'width: 200px'
        this.iconType = 'ios-close-empty'
        this.holder = '根据姓名搜索员工'
        this.nowPage = 1
        const allData = this.staffList
        const keyword = this.searchKeyword
        let result = []
        allData.forEach(function (item) {
          if (item.name.search(keyword) !== -1) {
            result.push(item)
          }
        })
        this.searchResult = result
        this.nowPage = 1
        this.staffShowList = this.getThisPageSearchResult(0)
        this.number = this.searchResult.length
        this.inSearch = true
      },
      getThisPageSearchResult (page) {
        return this.searchResult.slice(page * 10, page * 10 + 10)
      },
      endSearch () {
        this.searchKeyword = ''
        this.holder = ''
        this.iconType = 'ios-search-strong'
        this.inSearch = false
        this.searchWidth = 'width: 50px'
        this.data = this.getThisPageData(this.nowDataPage * 10 - 10)
        this.number = this.getDataSize()
      }
    }
  }
</script>
