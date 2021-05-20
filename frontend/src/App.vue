<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <TopBar v-if="isLoggedIn"></TopBar>
        <NavBarSecretaryPage v-if="isSecretaryLogged"></NavBarSecretaryPage>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import TopBar from "./components/TopBar";
import NavBarSecretaryPage from "./components/NavBarSecretaryPage";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import authHeader from "./api/http"

export default {
  name: "App",
  components: { TopBar, NavBarSecretaryPage },
  data: () => ({
  }),
  computed: {
    isLoggedIn: function () {
      return this.$store.state.auth.status.loggedIn;
    },
    isAdminLogged: function() {
      return this.$store.getters["auth/isAdmin"];
    },
    isSecretaryLogged: function() {
      return this.$store.getters["auth/isSecretary"];
    }
  },
  created: function () {
    // Create socket for doctor
    if(!this.isAdminLogged && !this.isSecretaryLogged) {
      var socket = new SockJS("http://localhost:8088/gs-guide-websocket");
      var stompClient = Stomp.over(socket);
      //{"Access-Control-Allow-Origin": "*"}
      stompClient.connect(authHeader(), function (frame) {
        console.log('Doctor connected to appointment socket: ' + frame);
        stompClient.subscribe('/topic/receiver', function (appointmentReceived) {
          console.log(JSON.parse(appointmentReceived.body).content);
        });
      });
    }
  }
};
</script>
