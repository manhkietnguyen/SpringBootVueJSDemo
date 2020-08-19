<template>
  <div class="container">
    <br />
    <h4>Accounts List</h4>
    <br /><br />
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
          <td>{{ account.active }}</td>
          <td>{{ account.role }}</td>
          <td>
            <button
              style="margin-right: 10px;"
              class="btn btn-warning"
              @click="editAccountClicked(account.id)"
            >
              <i class="fas fa-edit"></i>
            </button>
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
    };
  },
  methods: {
    retrieveTutorials() {
      AccountDataService.getAll()
        .then((response) => {
          this.accounts = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.retrieveTutorials();
  },
};
</script>
