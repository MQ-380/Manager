<template>
  <div id="delete">
    <notice :infoname="msg" v-if="showError" @closeModal="closeModal()"></notice>
    <success msg="成功删除员工" v-if="showSuccess" @closeModal="closeSuccess()"></success>
    <Modal v-model="showDelete" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>删除部门</span>
      </p>
      <div style="text-align:left" v-if="!isEmpty">
        <span><h4 style="color:#ff0325">您将要删除{{deleteItem.length}}个员工，请确认:</h4></span>
        <ol>
          <li v-for="(item, index) in deleteItem">
            {{index+1}}:硬功Id为{{item.staid}},员工姓名为{{item.name}}
          </li>
        </ol>
      </div>
      <div v-else>
        <span>您没有选中任何需要删除的员工。</span>
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
      getUrl: function () {
        var id = '?'
        this.deleteItem.forEach(function (item) {
          if (id !== '?') {
            id += '&'
          }
          id = id + 'staffID=' + item.staid
        })
        return 'http://localhost:8081/deleteStaff' + id
      }
    },
    components: {
      notice,
      success
    },
    methods: {
      confirmToDelete () {
        this.$http({
          url: this.getUrl,
          method: 'POST'
        }).then((response) => {
          if (response.body.status) {
            this.showSuccess = true
          } else {
            this.msg = '删除员工错误，请刷新表格后重试'
            this.showError = true
          }
        }, (response) => {
          this.msg = '删除员工错误，请检查您的网络连接'
          this.showError = true
        })
      },
      confirmToCancel () {
        this.$emit('cancel')
      },
      closeSuccess () {
        this.showSuccess = false
        this.$emit('closeModal')
      },
      closeModal () {
        this.showError = false
        this.$emit('cancel')
      }
    }
  }
</script>
