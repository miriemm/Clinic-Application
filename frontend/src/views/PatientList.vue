<template>
  <v-card>
    <v-card-title>
      Patients
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addPatient">Add Patient</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="patients"
      :search="search"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon
            color="green"
            small
            class="mr-2"
            @click="editPatient(item)"
        >
          mdi-pencil
        </v-icon>
      </template>
    </v-data-table>
    <PatientDialog
      v-bind:opened="dialogVisible"
      :patient="selectedItem"
      @refresh="refreshList"
    ></PatientDialog>
  </v-card>
</template>

<script>
import api from "../api";
import PatientDialog from "../components/PatientDialog";

export default {
  name: "PatientList",
  components: { PatientDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          value: "name",
        },
        {
          text: "Identity Card Number",
          value: "identityCardNr",
        },
        {
          text: "CNP",
          sortable: false,
          value: "personalNumericCode",
        },
        {
          text: "Date of Birth",
          value: "dateOfBirth",
        },
        {
          text: "Address",
          sortable: false,
          value: "address",
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
    editPatient(patient) {
      this.selectedItem = patient;
      this.dialogVisible = true;
    },
    addPatient() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.patients = await api.patients.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
