import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/accounts",
      name: "accounts",
      component: () => import("./components/AccountsListComponent"),
    },
    {
      path: "/addAccount",
      name: "add",
      component: () => import("./components/AddAccountComponent"),
    },
    {
      path: "/accounts/:id",
      name: "account-details",
      component: () => import("./components/EditAccountComponent"),
    },
  ],
});
