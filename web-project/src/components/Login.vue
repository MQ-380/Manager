<template>
  <div id="login">
    <Modal v-model="loginActive" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#f60;text-align:center">
        <span>用户登录</span>
      </p>
      <div style="text-align:center">
        <Form ref="formItem" :model="formItem" :label-width="80" :rules="ruleValidate">
          <Form-item label="用户名" prop="userName">
            <Input v-model="formItem.userName" placeholder="请输入"/>
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
      </div>
    </Modal>

    <Modal v-model="errorActive" >
      <p slot="header" style="color:#843534;text-align: center">
        <span>登录错误</span>
      </p>
      <div style="text-align: center">
        <span>{{msg}}</span>
      </div>
      <div slot="footer">
        <Button type="primary" @click="toLogin()">确定</Button>
      </div>
    </Modal>

  </div>
</template>

<script>
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
          userName: '',
          password: '',
          type: ''
        },
        type: '',
        APIurl: '',
        msg: '',
        ruleValidate: {
          userName: [{
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
    methods: {
      submit (item) {
        this.$refs[item].validate((valid) => {
          if (valid) {
            if (this.formItem.type === '1') {
              this.APIurl = 'http://localhost:8081/userLogin.action'
            } else {
              this.APIurl = 'http://localhost:8081/staffLogin.action'
            }
            this.$http({
              url: this.APIurl,
              methods: 'POST',
              params: {
                userName: this.formItem.userName,
                password: this.formItem.password
              }
            })
              .then((response) => {
                var info = response.body
                if (info.status) {
                  if (this.formItem.type === '1') {
                    this.type = '0'
                    this.id = info.id
                    this.$store.commit('LOGIN', {
                      type: this.type,
                      id: this.id
                    })
                    this.$router.push('/Admin')
                  } else {
                    this.type = info.type
                    this.id = info.id
                    this.$store.commit('LOGIN', {
                      type: this.type,
                      id: this.id
                    })
                    if (this.type === 1) {
                      this.$router.push('/Employee')
                    }
                  }
                } else {
                  this.loginActive = false
                  this.msg = info.msg
                  this.errorActive = true
                }
              }, (response) => {
                this.loginActive = false
                this.msg = '请检查网络'
                this.errorActive = true
              })
          } else {
            this.$Message.error('表单验证失败!')
          }
        })
      },
      toLogin () {
        this.errorActive = false
        this.loginActive = true
      }
    }
  }
</script>
