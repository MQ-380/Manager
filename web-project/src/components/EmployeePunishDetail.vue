<template>
  <div id="punishDetail">
    <Modal v-model="showDetail" :width="1000" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>奖惩详情</span>
      </p>
        <Table :data="detailShowList" :columns="columns" :height="523" stripe border></Table>
      <div style="margin: 10px;overflow: scroll">
        <div style="float: right;">
          <Page :total="number" :current="nowPage" @on-change="changePage"></Page>
        </div>
      </div>
      <div slot="footer">
        <Button type="primary" @click="cancel()">确定</Button>
      </div>
    </Modal>
  </div>
</template>

<style>
  .punish {
    color: #ac2925;
  }
  .reward {
    color: #5cb85c;
  }
</style>

<script>
  export default {
    data () {
      return {
        showDetail: true,
        showError: false,
        detailList: [],
        detailShowList: [],
        nowPage: 1,
        number: this.detail.length,
        columns: [
          {
            title: '日期',
            key: 'date',
            align: 'center'
          },
          {
            title: '金额',
            key: 'amount',
            align: 'center'
          },
          {
            title: '原因',
            key: 'description',
            align: 'center'
          },
          {
            title: '操作人',
            key: 'operator',
            align: 'center'
          }
        ]
      }
    },
    props: ['detail'],
    created () {
      this.detailList = this.detail
      this.detailList.forEach(function (item) {
        item.date = new Date(item.date).toLocaleDateString()
        if (item.type === 0) {
          item.amount = -item.amount
          item.cellClassName = {
            amount: 'punish'
          }
        } else {
          item.cellClassName = {
            amount: 'reward'
          }
        }
      })
      this.getThisPageData(0)
    },
    methods: {
      getThisPageData (page) {
        this.detailShowList = this.detailList.slice(page, (page + 1) * 10)
      },
      changePage () {
        this.getThisPageData(this.nowPage - 1)
      },
      cancel () {
        this.$emit('closeModal')
      }
    }
  }
</script>
