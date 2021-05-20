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
      <v-btn @click="addConsultation">Add Consultation</v-btn>
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
            @click="editConsultation(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
            color="red"
            small
            @click="deleteConsultation(item)"
        >
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
    <ConsultationDialog
        v-bind:opened="dialogVisible"
        :consultation="selectedItem"
        @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import api from "../api";
import ConsultationDialog from "../components/ConsultationDialog";

export default {
  name: "ConsultationList",
  components: { ConsultationDialog },
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
    editConsultation(consultation) {
      this.selectedItem = consultation;
      this.dialogVisible = true;
    },
    addConsultation() {
      this.dialogVisible = true;
    },
    async deleteConsultation(consultation) {
      await api.consultations.delete(consultation);
      await this.refreshList();
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.consultations = await api.consultations.allConsultations();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
