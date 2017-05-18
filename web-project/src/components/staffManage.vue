<template>
  <div id="staff">
    <notice v-if="showNotice" :infoname="noticemsg" @closeModal="cancel()"></notice>
    <Select v-model="selectDepartment" style="width:200px">
      <Option v-for="item in departmentList" :value="item.deid">{{item.name}}</Option>
    </Select>
  </div>
</template>

<script>
  import notice from './Notice'

  export default {
    data () {
      return {
        selectDepartment: '',
        departmentList: [],
        noticeMsg: '',
        showNotice: false
      }
    },
    components: {
      notice
    },
    methods: {
      getDepartmentList () {
        this.$http.post('http://localhost:8081/consultDepartment.action')
            .then((response) => {
              console.log('response true')
              this.$store.commit('SETDEPARTDATA', response.body.department)
              this.departmentList = response.body.department
            },
            (response) => {
              this.noticeMsg = '无法获取当前的部门列表，请检查您的网络连接'
              this.showNotice = true
            })
      },
      cancel () {
        this.showNotice = false
      }
    }
  }
</script>
