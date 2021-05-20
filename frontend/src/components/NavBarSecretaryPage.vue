<template>
  <v-card>
    <v-bottom-navigation
        :value="value"
        color="primary"
        horizontal
    >
    <v-btn @click="patientPage">
      <span>Patients</span>
      <v-icon>mdi-account-tie</v-icon>
    </v-btn>
    <v-btn @click="consultationPage">
      <span>Consultations</span>
      <v-icon>mdi-file-chart</v-icon>
    </v-btn>
    </v-bottom-navigation>
  </v-card>
</template>

<script>

import router from "../router";

export default {
  data: () => ({ value: 0 }),
  methods:{
    patientPage() {
      router.push("/patients");
    },
    consultationPage(){
      router.push("/consultations")
    },
  },
};
</script>

<style scoped></style>
