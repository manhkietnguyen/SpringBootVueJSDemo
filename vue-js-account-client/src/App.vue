<template>
  <div class="container">
    <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
      <!-- Brand/logo -->
      <a href="/" class="navbar-brand">
        <strong>KietNBM</strong>
      </a>

      <!-- Links -->
      <ul class="navbar-nav">
        <li class="nav-item active">
          <a href="/accounts" class="nav-link">Accounts</a>
        </li>
      </ul>
    </nav>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "app",
};
</script>

<style scope>
/* @import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css); */
@import url(https://use.fontawesome.com/releases/v5.6.3/css/all.css);
body {
  background-color: gray;
}
.container {
  background-color: white;
}
</style>
