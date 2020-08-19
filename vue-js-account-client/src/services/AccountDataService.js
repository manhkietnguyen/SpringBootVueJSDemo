import http from "../http-common";

class AccountDataService {
  getAll() {
    return http.get("/accounts");
  }

  create(data) {
    return http.post("/accounts", data);
  }
}

export default new AccountDataService();
