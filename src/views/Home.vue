<template>
  <el-main>
    <el-table
      :data = "files"
      border>
      <el-table-column
        prop="name"
        label="name"
        width="120">
      </el-table-column>
      <el-table-column
        prop="content"
        label="content">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="operate"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">EDIT</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="(size)=>{page.size = size; search()}"
      @current-change="(current)=>{page.current = current; page.number = current - 1; search()}"
      :current-page="page.current"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="page.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.totalElements">
    </el-pagination>
  </el-main>
</template>

<script>
// @ is an alias to /src
import { createClient } from "hal-rest-client";
import { stringify } from "qs"

const client = createClient("/api");

export default {
  name: 'Home',
  components: {
  },
  async mounted() {
    // let count = 23;
    // do {
    //   await client.create("/files", { id: null, name: "Thomas", content: "jkdsjjssdksdfsfd" });
    // } while(--count > 0)
    await this.search()
  },
  methods: {
    async search(){
      const {number, size} = this.page
      const filesPageResource = await client.fetchResource(`/files?${stringify({page: number,size})}`);
      const {files, page} = filesPageResource.props
      this.files = files.map(file => file.props)
      this.page = {
        ...page,
        current: page.number + 1
      }
    },
    handleClick(row) {
      console.log(row)
      const {id} = row
      this.$router.push({ name: 'Editor', params: { id }})
    }
  },
  data() {
    return {
      files: [],
      page: {
        current: 1,
        number: 0,
        size: 10,
        totalElements: 0
      },
    }
  }
}
</script>
