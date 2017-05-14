<template>
  <div id="depart">
    <router-view></router-view>
    <br><br>
    <div class='buttonCouple'>
      <Button type="primary" size="default" @click="toAdd">新增部门</Button>
      <Button type="error">删除部门</Button>
    </div>
    <div class="freshButton">
      <Button type="primary" shape="circle" @click="fresh" icon="ios-refresh">
      </Button>
    </div>
    <br><br>
    <br><br>
    <Table :data="data" :columns="column1" stripe border></Table>
    <div style="margin: 10px;overflow: hidden">
      <div style="float: right;">
        <Page :total="3" :current="1" @on-change="changePage"></Page>
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
</style>

<script>
  import Notice from './CheckNetworkNotice'
  import Add from './AddInformation'
  export default {
    data () {
      return {
        data: this.getData(),
        column1: [
          {
            type: 'selection',
            width: 60,
            align: 'center'
          },
          {
            title: '部门编号',
            width: 100,
            key: 'ID',
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
      Notice,
      Add
    },
    methods: {
      getData () {
        var fakeData = {
          department: [
            {
              ID: '001',
              name: '人事部'
            },
            {
              ID: '002',
              name: '财务部'
            },
            {
              ID: '003',
              name: '人力资源部'
            }
          ]
        }
        this.$http.post('https://reqres.in/api/users', fakeData)
            .then((response) => {
              this.$store.commit('SETDEPARTDATA', response.body.department)
            },
            (response) => {
              this.$store.commit('SETNOTICE', {
                name: '部门',
                show: true
              })
              this.$router.push('/Notice')
            })
        return this.$store.state.departState.departData
      },
      changePage () {
        this.data = this.getData()
      },
      toAdd () {
        this.$router.push('/Add')
      },
      fresh () {
        this.data = this.getData()
      }
    }
  }
</script>
