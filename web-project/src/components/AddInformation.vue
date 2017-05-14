<template>
  <div id="add">
    <Modal v-model="showAddDepart" @on-ok="confirmToAdd('department')" @on-cancel="confirmToCancel">
      <p slot="header" style="color:#843534;text-align:left">
        <span>新增部门</span>
      </p>
      <div style="text-align:center">
        <Form ref="formItem" :model="departFormItem" :label-width="80" :rules="departValidate">
          <Form-item label="部门名称" prop="name">
            <Input v-model="departFormItem.name" placeholder="请输入"/>
          </Form-item>
        </Form>
      </div>
    </Modal>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        showAddDepart: true,
        departFormItem: {
          name: ''
        },
        departValidate: {
          name: [{
            required: true,
            message: '部门名称不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      confirmToAdd (type) {
        if (type === 'department') {
          this.$http.post('https://reqres.in/api/users')
            .then((response) => {
              this.$router.go(-1)
            },
              (response) => {

              })
        }
      },
      confirmToCancel () {
        this.$router.go(-1)
      }
    }
  }
</script>
