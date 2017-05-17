<template>
  <div id="DepartmentEdit">
    <notice :infoname="msg" v-if="noticeShow" @closeModal="closeNotice()"></notice>
    <success msg="修改部门信息成功" v-if="successShow" @closeModal="closeSuccess()"></success>
    <Modal v-model="showModal" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>修改部门信息</span>
      </p>
      <div style="text-align:center">
        <Form ref="departmentName" :model="departmentName" :label-width="80" :rules="departValidator">
          <Form-item label="部门名称" prop="name">
            <Input v-model="departmentName.name" placeholder="请输入"/>
          </Form-item>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" @click="editName('departmentName')">确定</Button>
        <Button type="ghost" @click="cancel()">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import success from './Success'
  import notice from './Notice'
  export default {
    name: 'DepartmentEdit',
    props: ['department'],
    data () {
      const TypeValidate = (rule, value, callback) => {
        if (value === this.oldName) {
          callback(new Error('请修改不同名称，若无需修改请退出。'))
        } else if (value === '') {
          callback(new Error('部门名称不能为空'))
        } else {
          callback()
        }
      }
      return {
        oldName: '',
        successShow: false,
        noticeShow: false,
        showModal: true,
        msg: '',
        id: '',
        departmentName: {
          name: ''
        },
        departValidator: {
          name: [{
            required: true,
            validator: TypeValidate,
            trigger: 'blur'
          }]
        }
      }
    },
    mounted: function () {
      this.$watch('department', function (newVal, oldVal) {
        console.log('newVal')
        this.oldName = newVal.name
      })
      this.oldName = this.department.name
      this.id = this.department.deid
      this.departmentName.name = this.oldName
    },
    components: {
      success,
      notice
    },
    methods: {
      editName (item) {
        this.$refs[item].validate((validate) => {
          if (validate) {
            this.$http({
              url: 'http://localhost:8081/editDepartment',
              method: 'POST',
              params: {
                id: this.id,
                name: this.departmentName.name
              }
            }).then((response) => {
              if (response.status) {
                this.successShow = true
              } else {
                this.msg = '无法修改部门名称'
                this.noticeShow = true
              }
            }, (response) => {
              this.msg = '无法修改部门名称，请检查网络连接'
              this.noticeShow = true
            })
          } else {
            this.$Message.error('表单验证失败')
          }
        })
      },
      closeSuccess () {
        this.successShow = false
        this.$emit('closeModal')
      },
      closeNotice () {
        this.noticeShow = false
      },
      closeModal () {
        this.$emit('closeModal')
      },
      cancel () {
        this.$emit('closeModal')
      }
    }
  }
</script>
