import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    // secretary operations
    allConsultations() {
        return HTTP.get(BASE_URL + "/consultations", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(consultation) {
        return HTTP.post(BASE_URL + "/consultations", consultation, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(consultation) {
        return HTTP.patch(BASE_URL + "/consultations", consultation, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(consultation) {
        return HTTP.delete(BASE_URL + "/consultations", { data: consultation, headers: authHeader() }).then(
            (response) => {
                return response.data;
            });
    },
    // doctor operations
    allPastConsultations() {
        return HTTP.get(BASE_URL + "/consultations/findAllPastConsultations/", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    addDetails(consultation) {
        return HTTP.patch(BASE_URL + "/consultations/addDetails", consultation, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
};

