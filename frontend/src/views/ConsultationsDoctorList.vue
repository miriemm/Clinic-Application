<template>
  <v-card>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="consultations"
        :search="search"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon
            color="green"
            small
            class="mr-2"
            @click="addDetails(item)"
        >
          mdi-comment-outline
        </v-icon>
      </template>
    </v-data-table>
    <ConsultationDetails
        v-bind:opened="dialogVisible"
        :consultation="selectedItem"
        @refresh="refreshList"
    ></ConsultationDetails>
  </v-card>
</template>

<script>
import api from "../api";
import ConsultationDetails from "@/components/ConsultationDetails";
export default {
  name: "ConsultationsDoctorList",
  components: { ConsultationDetails },
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        {
          text: "Patient Name",
          align: "start",
          value: "patientName",
        },
        {
          text: "Doctor Name",
          value: "doctorName",
        },
        {
          text: "Date",
          sortable: false,
          value: "dateOfConsultation",
        },
        {
          text: "Description",
          value: "description",
        },
        {
          text: "Actions",
          value: "actions"}
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    addDetails(consultation) {
      this.selectedItem = consultation;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.consultations = await api.consultations.allPastConsultations();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
