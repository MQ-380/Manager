<template>
  <div id="depart" class="canRoll">
    <edit v-if="showEdit" :department="editInfo" @closeModal="closeAndFresh()" @cancel="closeModal()"></edit>
    <add v-if="showAdd" @closeAdd="closeAndFresh()" @cancel="closeModal()"></add>
    <delDepart v-if="showDelete" :deleteItem="deleteItem" @closeDelete="closeAndFresh()"
               @cancel="closeModal()"></delDepart>
    <notice v-if="showNotice" infoname=“无法查询部门信息，请检查网络情况”  @closeModal="closeModal()"></notice>
    <div class='buttonCouple'>
      <Button type="primary" @click="toAdd">新增部门</Button>
      <Button type="error" @click="toDelete">删除部门</Button>
    </div>
    <div class="freshButton">
      <Button type="primary" shape="circle" @click="fresh" icon="ios-refresh">
      </Button>
    </div>
    <br><br>
    <Input v-model="searchKeyword"  :style="searchWidth" :icon="iconType" class="freshButton" @on-focus="startSearch()"
    @on-click="endSearch()" @on-change="startSearch()" :placeholder="holder"/>
    <br><br>
    <Table :data="data" :columns="column1" :height="523" @on-select="addDeleteItem" @on-select-all="addDeleteItem"
           stripe border></Table>
    <div style="margin: 10px;overflow: scroll">
      <div style="float: right;">
        <Page :total="number" :current="nowDataPage" @on-change="changePage"></Page>
      </div>
    </div>
  </div>
</template>

<style>
  .buttonCouple {
    float: left
  }

  .freshButton {
    float: right
  }

  .canRoll {
    overflow: scroll;
  }
</style>

<script>
  import notice from './Notice'
  import add from './DepartmentAdd'
  import edit from './DepartmentEdit'
  import delDepart from './DepartmentDelete'

  export default {
    data () {
      return {
        data: this.initForm(),
        number: this.getDataSize(),
        nowDataPage: 1,
        currentPage: 1,
        showNotice: false,
        showAdd: false,
        showEdit: false,
        showDelete: false,
        editInfo: {},
        deleteItem: [],
        searchKeyword: '',
        searchResult: [],
        searchWidth: 'width: 50px',
        inSearch: false,
        iconType: 'ios-search-strong',
        holder: '',
        column1: [
          {
            type: 'selection',
            width: 60,
            align: 'center'
          },
          {
            title: '部门编号',
            width: 100,
            key: 'deid',
            align: 'center'
          },
          {
            title: '部门名称',
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
                      this.editInfo = this.$store.state.departState.departData[this.currentPage * 10 - 10 + params.index]
                      this.showEdit = true
                    }
                  }
                }, '更新信息')
              ])
            }
          }
        ]
      }
    },
    components: {
      notice,
      add,
      edit,
      delDepart
    },
    methods: {
      getData () {
        this.$http.post('http://localhost:8081/consultDepartment.action')
            .then((response) => {
              console.log('response true')
              this.$store.commit('SETDEPARTDATA', response.body.department)
            },
            (response) => {
              this.showNotice = true
            })
        return this.$store.state.departState.departData
      },
      getDataSize () {
        return this.$store.state.departState.departData.length
      },
      initForm () {
        this.getData()
        return this.getThisPageData(0)
      },
      getThisPageData (begin) {
        if (!this.inSearch) {
          return this.$store.state.departState.departData.slice(begin, begin + 10)
        } else {
          return this.searchResult.slice(begin, begin + 10)
        }
      },
      changePage (current) {
        console.log(current)
        this.currentPage = current
        this.data = this.getThisPageData(current * 10 - 10)
      },
      toAdd () {
        this.showAdd = true
      },
      toDelete () {
        this.showDelete = true
      },
      addDeleteItem (selected) {
        let items = []
        selected.forEach(function (item, array) {
          items.push(item)
        })
        this.deleteItem = items
      },
      fresh () {
        this.getData()
        this.data = this.getThisPageData(this.currentPage * 10 - 10)
        this.number = this.getDataSize()
      },
      closeModal () {
        this.showError = false
        this.showAdd = false
        this.showEdit = false
        this.showDelete = false
      },
      closeAndFresh () {
        this.$http.post('http://localhost:8081/consultDepartment.action')
            .then((response) => {
              console.log('response true')
              this.$store.commit('SETDEPARTDATA', response.body.department)
              this.data = this.getThisPageData(this.currentPage * 10 - 10)
            },
            (response) => {
              this.showNotice = true
            })
        this.deleteItem = []
        this.closeModal()
      },
      startSearch () {
        this.searchWidth = 'width: 200px'
        this.iconType = 'ios-close-empty'
        this.holder = '根据名称搜索部门'
        this.nowDataPage = 1
        const allData = this.$store.state.departState.departData
        const keyword = this.searchKeyword
        let result = []
        allData.forEach(function (item) {
          if (item.name.search(keyword) !== -1) {
            result.push(item)
          }
        })
        this.searchResult = result
        this.nowDataPage = 1
        this.data = this.getThisPageSearchResult(0)
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
        this.data = this.getThisPageData(this.nowDataPage * 10 - 10)
        this.number = this.getDataSize()
        this.searchWidth = 'width: 50px'
      }
    }
  }
</script>
