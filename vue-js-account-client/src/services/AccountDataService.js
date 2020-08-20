import http from "../http-common";

class AccountDataService {
  getAll() {
    return http.get("/accounts");
  }

  create(data) {
    return http.post("/accounts", data);
  }

  findByUsername(username) {
    return http.get(`/accounts?username=${username}`);
  }

  get(id) {
    return http.get(`/accounts/${id}`);
  }

  update(id, data) {
    return http.put(`/accounts/${id}`, data);
  }
}

export default new AccountDataService();
