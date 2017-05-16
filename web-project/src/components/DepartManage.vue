<template>
  <div id="depart" class="canRoll">
    <add v-if="showAdd" @closeAdd="closeAdd()"></add>
    <notice v-if="showNotice" :infoName="查询部门信息" @closeModal="closeModal()"></notice>
    <div class='buttonCouple'>
      <Button type="primary"  @click="toAdd">新增部门</Button>
      <Button type="error">删除部门</Button>
    </div>
    <div class="freshButton">
      <Button type="primary" shape="circle" @click="fresh" icon="ios-refresh">
      </Button>
    </div>
    <br><br>
    <br><br>
    <Table :data="data" :columns="column1" :height="523" stripe border></Table>
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
  import notice from './CheckNetworkNotice'
  import add from './AddDepartInformation'
  export default {
    data () {
      return {
        data: this.initForm(),
        number: this.getDataSize(),
        nowDataPage: 1,
        showNotice: false,
        showAdd: false,
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
                      this.show(params.index)
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
      add
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
        this.data = this.getThisPageData(current * 10 - 10)
      },
      toAdd () {
        this.showAdd = true
      },
      fresh () {
        this.getData()
        this.data = this.getThisPageData(0)
        this.number = this.getDataSize()
      },
      closeModal () {
        this.showError = false
      },
      closeAdd () {
        this.fresh()
        this.showAdd = false
      }
    }
  }
</script>
