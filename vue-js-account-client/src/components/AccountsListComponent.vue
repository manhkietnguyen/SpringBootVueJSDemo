<template>
  <div class="container">
    <br />
    <h4>
      <strong>Accounts List</strong>
    </h4>
    <br />
    <div v-if="success">
      <div class="alert alert-success" style="width: 400px;">{{ message }}</div>
    </div>
    <div class="row">
      <div class="col-md-4" style="float: left;">
        <button class="btn btn-primary" @click="showAddModal">Add Account</button>
      </div>
      <div class="input-group col-md-4 offset-md-4" style="float: right;">
        <input
          type="text"
          class="form-control"
          placeholder="Search by username"
          v-model="searchUsername"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-info"
            type="button"
            @click="
              page = 1;
              retrieveAccounts();
            "
          >
            <i class="fa fa-search"></i>
          </button>
        </div>
      </div>
    </div>
    <hr />
    <table class="table table-striped">
      <thead style="text-align: center;">
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Company Name</th>
          <th>Country</th>
          <th>Language</th>
          <th>Role</th>
          <th>Active</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="account in accounts" :key="account.id">
          <td>{{ account.id }}</td>
          <td>{{ account.username }}</td>
          <td>{{ account.firstName }}</td>
          <td>{{ account.lastName }}</td>
          <td>{{ account.companyName }}</td>
          <td>{{ account.country }}</td>
          <td>{{ account.language }}</td>
          <td>{{ account.role }}</td>
          <td>{{ account.active }}</td>
          <td>
            <!-- <a
              :href="'/accounts/' + account.id"
              class="btn btn-warning"
              style="margin-right: 10px;"
            >
              <i class="fas fa-edit"></i>
            </a>-->
            <button class="btn btn-warning" @click="showEditModal(account.id)">
              <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-danger" @click="showDeleteModal(account.id)">
              <i class="fas fa-trash-alt"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="row" style="width: 90%; margin: auto;">
      <b-pagination
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        prev-text="Prev"
        next-text="Next"
        @change="handlePageChange"
      ></b-pagination>
      <div class="mb-3" style="margin-left: auto;">
        Items per Page:
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <option v-for="size in pageSizes" :key="size" :value="size">{{ size }}</option>
        </select>
      </div>
    </div>

    <div>
      <delete-modal
        ref="newModal"
        :accountid="accountId"
        @deleteAccount="deleteAccount(id = $event)"
      ></delete-modal>
    </div>
    <div>
      <add-modal ref="addModal" @addAccount="retrieveAccounts" @success="addSuccess"></add-modal>
    </div>
    <div>
      <edit-modal ref="editModal" :account="account" @success="editSuccess"></edit-modal>
    </div>
  </div>
</template>

<script>
import AccountDataService from "../services/AccountDataService";
import DeleteModal from "../components/DeleteModal";
import AddModal from "../components/AddAccountModal";
import EditModal from "../components/EditAccountModal";

export default {
  name: "accounts-list-paging",
  components: {
    deleteModal: DeleteModal,
    addModal: AddModal,
    editModal: EditModal,
  },
  data() {
    return {
      accounts: [],
      account: [],
      message: "",
      username: "",
      accountId: "",
      success: "",
      isModalVisible: false,
      searchUsername: "",
      page: 1,
      count: 0,
      pageSize: 3,
      pageSizes: [3, 6, 9],
    };
  },
  methods: {
    getRequestParams(page, pageSize, searchUsername) {
      let params = {};
      if (searchUsername) {
        params["username"] = searchUsername;
      }
      if (page) {
        params["page"] = page - 1;
      }
      if (pageSize) {
        params["size"] = pageSize;
      }
      return params;
    },
    retrieveAccounts() {
      const params = this.getRequestParams(
        this.page,
        this.pageSize,
        this.searchUsername
      );

      AccountDataService.getAllPng(params)
        .then((response) => {
          this.accounts = response.data.listAccountDTO;
          this.count = response.data.totalItems;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    handlePageChange(value) {
      this.page = value;
      this.retrieveAccounts();
    },

    handlePageSizeChange(event) {
      this.pageSize = event.target.value;
      this.page = 1;
      this.retrieveAccounts();
    },

    showDeleteModal(id) {
      this.accountId = id;
      this.$refs.newModal.showModal();
    },

    showAddModal() {
      this.$refs.addModal.showAddModal();
    },
    deleteAccount(id) {
      AccountDataService.delete(id)
        .then(() => {
          this.retrieveAccounts();
          this.success = true;
          this.message = "Delete Account Successfully!";
        })
        .catch((e) => {
          console.log(e);
        });
    },
    addSuccess() {
      this.success = true;
      this.message = "Add Account Successfully!";
    },
    showEditModal(id) {
      AccountDataService.get(id).then((response) => {
        this.account = response.data;
      });
      this.$refs.editModal.showEditModal();
    },
    editSuccess() {
      this.success = true;
      this.message = "Account was updated successfully!";
      this.retrieveAccounts();
    },
  },
  mounted() {
    this.retrieveAccounts();
  },
};
</script>

<style>
h4 {
  text-align: center;
}
/* 
#searchUsername {
  float: right;
} */

#listContainer {
  height: 1000px;
}
</style>
