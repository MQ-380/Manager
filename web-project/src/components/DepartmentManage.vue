<template>
  <div id="depart" class="canRoll">
    <edit v-if="showEdit" :department="editInfo"  @closeModal="closeAndFresh()"></edit>
    <add v-if="showAdd" @closeAdd="closeAndFresh()"></add>
    <delDepart v-if="showDelete" :deleteItem="deleteItem" @closeDelete="closeAndFresh()"></delDepart>
    <notice v-if="showNotice" infoname=“无法查询部门信息，请检查网络情况” @closeModal="closeModal()"></notice>
    <div class='buttonCouple'>
      <Button type="primary"  @click="toAdd">新增部门</Button>
      <Button type="error" @click="toDelete">删除部门</Button>
    </div>
    <div class="freshButton">
      <Button type="primary" shape="circle" @click="fresh" icon="ios-refresh">
      </Button>
    </div>
    <br><br>
    <br><br>
    <Table :data="data" :columns="column1" :height="523" @on-select="addDeleteItem" stripe border></Table>
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
        return this.$store.state.departState.departData.slice(begin, begin + 10)
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
        this.getData()
        this.data = this.getThisPageData(this.currentPage * 10 - 10)
        this.closeModal()
      }
    }
  }
</script>
