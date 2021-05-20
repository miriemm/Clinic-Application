<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Add consultation" : "Edit consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.patientName" label="Patient Name" />
          <v-text-field v-model="consultation.doctorName" label="Doctor Name" />
          <v-text-field v-model="consultation.dateOfConsultation" label="Date" />
          <v-text-field v-model="consultation.description" label="Description" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import {BASE_URL} from "@/api/http";

export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    onHidden(){
      this.opened = false;
    },
    persist() {
      if (this.isNew) {
        api.consultations
            .create({
              patientName: this.consultation.patientName,
              doctorName: this.consultation.doctorName,
              dateOfConsultation: this.consultation.dateOfConsultation,
              description: this.consultation.description,

            })
            .then(() => this.$emit("refresh"));

        // notify the doctor about appointment
        var socket = new SockJS(BASE_URL + '/gs-guide-websocket');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
          console.log('Secretary connected to appointment socket: ' + frame);
        });
        stompClient.send("/app/sender", {},
            JSON.stringify({
              'patientName' : this.consultation.patientName
            , 'doctorName' : this.consultation.doctorName
            , 'dateOfConsultation' : this.consultation.dateOfConsultation
            }));

        stompClient.disconnect();
        console.log('Secretary disconnected from appointment socket');

      } else {
        api.consultations
            .edit({
              id: this.consultation.id,
              patientName: this.consultation.patientName,
              doctorName: this.consultation.doctorName,
              dateOfConsultation: this.consultation.dateOfConsultation,
              description: this.consultation.description,
            })
            .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>
