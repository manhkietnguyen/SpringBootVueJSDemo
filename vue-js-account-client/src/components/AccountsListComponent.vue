<template>
  <div class="container">
    <br />
    <h4><strong>Accounts List</strong></h4>
    <br />
    <div class="col-md-4" id="searchUsername">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Search by username"
          v-model="username"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="searchUsername"
          >
            <i class="fa fa-search"></i>
          </button>
        </div>
      </div>
    </div>
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
            <a :href="'/accounts/' + account.id" class="btn btn-warning"
              ><i class="fas fa-edit"></i
            ></a>
            <button
              class="btn btn-danger"
              @click="deleteAccountClicked(account.id)"
            >
              <i class="fas fa-trash-alt"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import AccountDataService from "../services/AccountDataService";

export default {
  name: "accounts-list",
  data() {
    return {
      accounts: [],
      message: null,
      username: "",
    };
  },
  methods: {
    retrieveAccounts() {
      AccountDataService.getAll()
        .then((response) => {
          this.accounts = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveAccounts();
    },

    searchUsername() {
      AccountDataService.findByUsername(this.username)
        .then((response) => {
          this.accounts = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
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

#searchUsername {
  float: right;
}
</style>
