<template>
  <div class="submit-form">
    <h4>Add Account</h4>
    <br />
    <div v-if="error">
      <div class="alert alert-danger">{{errorMessage}}</div>
    </div>
    <div v-if="success">
      <div class="alert alert-success">{{successMessage}}</div>
    </div>
    <div class="form-group">
      <label for="username">Username</label>
      <input
        type="text"
        name="username"
        class="form-control"
        id="username"
        required
        v-model="account.username"
      />
    </div>
    <div class="form-group">
      <label for="password">Password</label>
      <input
        type="password"
        name="password"
        class="form-control"
        id="password"
        required
        v-model="account.password"
      />
    </div>
    <div class="form-group">
      <label for="confirmPassword">Confirm Password</label>
      <input
        type="password"
        name="confirmPassword"
        class="form-control"
        id="confirmPassword"
        required
        v-model="account.confirmPassword"
      />
    </div>
    <div class="form-group">
      <label for="firstName">First Name</label>
      <input
        type="text"
        name="firstName"
        class="form-control"
        id="firstName"
        required
        v-model="account.firstName"
      />
    </div>
    <div class="form-group">
      <label for="lastName">Last Name</label>
      <input
        type="text"
        name="lastName"
        class="form-control"
        id="lastName"
        required
        v-model="account.lastName"
      />
    </div>
    <div class="form-group">
      <label for="companyName">Company Name</label>
      <input
        type="text"
        name="companyName"
        class="form-control"
        id="companyName"
        required
        v-model="account.companyName"
      />
    </div>
    <div class="form-group">
      <label for="country">Country</label>
      <input
        type="text"
        name="country"
        class="form-control"
        id="country"
        required
        v-model="account.country"
      />
    </div>
    <div class="form-group">
      <label for="language">Language</label>
      <input
        type="text"
        name="language"
        class="form-control"
        id="language"
        required
        v-model="account.language"
      />
    </div>
    <div class="form-group">
      <label for="role">Role</label>
      <input type="text" name="role" class="form-control" id="role" required v-model="account.role" />
    </div>
    <button @click="saveAccount" class="btn btn-success">Submit</button>
  </div>
</template>

<script>
import AccountDataService from "../services/AccountDataService";
export default {
  name: "add-account",
  data() {
    return {
      account: {
        id: null,
        username: "",
        password: "",
        confirmPassword: "",
        firstName: "",
        lastName: "",
        companyName: "",
        country: "",
        language: "",
        role: "",
        active: "",
      },
      error: false,
      success: false,
      errorMessage: "",
      successMessage: "",
    };
  },
  methods: {
    validateAccount() {
      if (
        this.account.username.length < 1 ||
        this.account.password.length < 1 ||
        this.account.confirmPassword.length < 1 ||
        this.account.firstName.length < 1 ||
        this.account.lastName.length < 1
      ) {
        this.error = true;
        this.errorMessage = "Please fill out the form";
        return false;
      }
    },
    saveAccount() {
      var data = {
        username: this.account.username,
        password: this.account.password,
        confirmPassword: this.account.confirmPassword,
        firstName: this.account.firstName,
        lastName: this.account.lastName,
        companyName: this.account.companyName,
        country: this.account.country,
        language: this.account.language,
        role: this.account.role,
        active: "Yes",
      };

      this.validateAccount();

      AccountDataService.create(data)
        .then((response) => {
          if (response.status == 400) {
            this.error = true;
            this.success = false;
            this.errorMessage = response.data.errorMessage;
          } else if (response.status == 201) {
            this.success = true;
            this.error = false;
            this.successMessage = response.data.successMessage;
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    newAccount() {
      this.account = {};
    },
  },
};
</script>
