<template>
  <div id="login">
    <Modal v-model="loginActive">
      <p slot="header" style="color:#f60;text-align:center">
        <span>用户登录</span>
      </p>
      <div style="text-align:center">
        <Form ref="formItem" :model="formItem" :label-width="80" :rules="ruleValidate">
          <Form-item label="用户名" prop="user">
            <Input v-model="formItem.user" placeholder="请输入"/>
          </Form-item>
          <Form-item label="密码" prop="password">
            <Input type="password" v-model="formItem.password" placeholder="请输入"/>
          </Form-item>
          <Form-item label="账户类别" prop="type">
            <Select v-model="formItem.type">
              <Option value="1">管理员</Option>
              <Option value="2">员工账户</Option>
            </Select>
          </Form-item>
        </Form>
      </div>
      <div slot="footer">
          <Button type="primary" @click="submit('formItem')">提交</Button>
          <Button type="ghost" style="margin-left: 8px">取消</Button>
      </div>
    </Modal>

    <Modal v-model="errorActive">
      <p slot="header" style="color:#843534;text-align: center">
        <span>登录错误</span>
      </p>
      <div style="text-align: center">
        <span>{{msg}}</span>
      </div>
    </Modal>
  </div>
</template>

<script>
  import info from '../vuex/store'

  export default{
    name: 'login',
    data () {
      const TypeValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择账户类别'))
        } else {
          callback()
        }
      }
      return {
        loginActive: true,
        errorActive: false,
        formItem: {
          user: '',
          password: '',
          type: ''
        },
        type: '',
        APIurl: 'https://reqres.in/api/users',
        msg: '',
        fakeAPI: {
          status: '1',
          type: '1',
          id: '1'
        },
        ruleValidate: {
          user: [{
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '密码不能为空',
            trigger: 'blur'
          }],
          type: [{
            validator: TypeValidate,
            trigger: 'blur'
          }]
        }
      }
    },
    info,
    methods: {
      submit (item) {
        this.$refs[item].validate((valid) => {
          if (valid) {
            var fake = JSON.stringify(this.fakeAPI)
            if (this.formItem.type === '1') {
              this.APIurl = 'https://reqres.in/api/users'
            } else {
              this.APIurl = ''
            }
            this.$http.post(this.APIurl, fake)
              .then((response) => {
                var info = response.body
                if (info.status === '1') {
                  this.type = info.type
                  this.id = info.id
                  var tmp = {
                    type: this.type,
                    id: this.id
                  }
                  this.$store.commit('LOGIN', tmp)
                  this.$router.push('/Admin')
                } else {
                  this.loginActive = false
                  this.msg = info.msg
                  this.errorActive = true
                }
              }, (response) => {
              })
          } else {
            this.$Message.error('表单验证失败!')
          }
        })
      }
    }
  }
</script>
