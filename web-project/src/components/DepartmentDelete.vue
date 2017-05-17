<template>
  <div id="delete">
    <notice :infoname="msg" v-if="showError" @closeModal="closeModal()"></notice>
    <success msg="成功删除部门" v-if="showSuccess" @closeModal="closeSuccess()"></success>
    <Modal v-model="showDelete" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>删除部门</span>
      </p>
      <div style="text-align:center" v-if="!isEmpty">
        <span>您将要删除以下几个部门</span>
        <ul>
          <li v-for="(item, index) in deleteItem">
            部门Id为{{item.deid}},部门名称为{{item.name}}
          </li>
        </ul>
      </div>
      <div v-else>
        <span>您没有选中任何需要删除的部门。</span>
      </div>
      <div slot="footer">
        <Button v-if="!isEmpty" type="primary" @click="confirmToDelete()">确定</Button>
        <Button v-if="!isEmpty" type="ghost" @click="confirmToCancel()">取消</Button>
        <Button v-if="isEmpty" type="primary" @click="confirmToCancel()">确定</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import notice from './Notice'
  import success from './Success'

  export default {
    props: ['deleteItem'],
    data () {
      return {
        showDelete: true,
        showSuccess: false,
        showError: false,
        msg: ''
      }
    },
    computed: {
      isEmpty: function () {
        return this.deleteItem.length === 0
      },
      deleteID: function () {
        var id = []
        this.deleteItem.forEach(function (item, array) {
          id.push(item)
        })
        return id
      }
    },
    components: {
      notice,
      success
    },
    methods: {
      confirmToDelete () {
        this.$http({
          url: 'http://localhost:8081/deleteDepartment',
          method: 'POST',
          data: {
            departmentID: this.deleteID
          }
        }).then((response) => {
          if (response.status) {
            this.showSuccess = true
          } else {
            this.msg = '删除部门错误，请刷新表格后重试'
            this.showError = true
          }
        }, (response) => {
          this.msg = '删除部门错误，请检查您的网络连接'
          this.showError = true
        })
      },
      confirmToCancel () {
        this.$emit('closeDelete')
      },
      closeSuccess () {
        this.showSuccess = false
        this.$emit('closeDelete')
      },
      closeModal () {
        this.showError = false
      }
    }
  }
</script>
