<template>
  <div id="add">
    <notice infoname="无法新增部门，请检查您的网络状况" v-if="showError" @closeModal="closeModal()"></notice>
    <success msg="成功添加部门" v-if="showSuccess" @closeModal="closeSuccess()"></success>
    <Modal v-model="showAdd" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>新增部门</span>
      </p>
      <div style="text-align:center">
        <Form ref="departFormItem" :model="departFormItem" :label-width="80" :rules="departValidate">
          <Form-item label="部门名称" prop="departmentname">
            <Input v-model="departFormItem.departmentname" placeholder="请输入"/>
          </Form-item>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" @click="confirmToAdd('departFormItem')">确定</Button>
        <Button type="ghost" @click="confirmToCancel()">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import notice from './Notice'
  import success from './Success'

  export default {
    data () {
      return {
        showAdd: true,
        showError: false,
        showSuccess: false,
        departFormItem: {
          departmentname: ''
        },
        departValidate: {
          departmentname: [{
            required: true,
            message: '部门名称不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    components: {
      notice,
      success
    },
    methods: {
      confirmToAdd (item) {
        this.$refs[item].validate((valid) => {
          if (valid) {
            this.$http({
              method: 'POST',
              params: this.departFormItem,
              url: 'http://localhost:8081/addDepartment.action'
            })
              .then((response) => {
                if (response.status) {
                  this.showSuccess = true
                }
              },
              (response) => {
                this.showError = true
              })
          } else {
            this.$Message.error('表单验证失败')
          }
        })
      },
      confirmToCancel () {
        this.$emit('closeAdd')
      },
      closeModal () {
        this.showError = false
      },
      closeSuccess () {
        this.showSuccess = false
        this.$emit('closeAdd')
      }
    }
  }
</script>
