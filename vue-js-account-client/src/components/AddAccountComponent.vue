<template>
  <div class="submit-form">
    <br />
    <h4><strong>Add Account</strong></h4>
    <br />
    <div v-if="error" class="errorAl">
      <div class="alert alert-danger" style="width: 400px">
        {{ errorMessage }}
      </div>
    </div>
    <div v-if="success">
      <div class="alert alert-success" id="successAl">{{ successMessage }}</div>
    </div>
    <div class="row">
      <div class="col-6">
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
      </div>
      <div class="col-6">
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
          <select
            name="language"
            id="language"
            v-model="account.language"
            class="form-control"
          >
            <option disabled value="">---Select Language---</option>
            <option value="Viet Nam">Viet Nam</option>
            <option value="Japan">Japan</option>
            <option value="China">China</option>
          </select>
        </div>
        <div class="form-group">
          <label for="role">Role</label>
          <select
            name="role"
            id="role"
            v-model="account.role"
            class="form-control"
          >
            <option disabled value="">---Select Role---</option>
            <option value="ROLE_ADMIN">ADMIN</option>
            <option value="ROLE_USER">USER</option>
          </select>
        </div>
      </div>
    </div>

    <div class="submitBtn">
      <button @click="saveAccount" class="btn btn-success">
        <i class="fa fa-check-square"></i> Submit
      </button>
    </div>
    <br />
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
      } else {
        return true;
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
      if (this.validateAccount()) {
        AccountDataService.create(data)
          .then((response) => {
            if (response.status == 200) {
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
      }
    },
    newAccount() {
      this.account = {};
    },
  },
};
</script>

<style>
h4 {
  text-align: center;
}
.submitBtn {
  text-align: center;
}
.errorAl {
  text-align: center;
}
</style>
