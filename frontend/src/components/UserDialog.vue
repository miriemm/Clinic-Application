<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create user" : "Edit user" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="user.firstName" label="First Name" />
          <v-text-field v-model="user.lastName" label="Last Name" />
          <v-text-field v-model="user.name" label="Username" />
          <v-text-field v-model="user.email" label="Email" />
          <v-text-field v-model="user.password" label="Password" />
          <v-text-field v-if="isNew" newRole="" v-model="newRole" label="Role" />
          <v-text-field v-else v-model="user.roles[0]" label="Role" />
<!--          <v-text-field v-model="user.role" label="Role" />-->
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

export default {
  name: "UserDialog",
  props: {
    user: Object,
    opened: Boolean,
    newRole: String
  },
  methods: {
    onHidden(){
      this.opened = false;
    },
    persist() {
      if (this.isNew) {
        api.users
            .create({
              firstName: this.user.firstName,
              lastName: this.user.lastName,
              username: this.user.name,
              email: this.user.email,
              password: this.user.password,
              // role: this.user.role,
               roles: [this.newRole],
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.users
            .edit({
              id: this.user.id,
              firstName: this.user.firstName,
              lastName: this.user.lastName,
              username: this.user.name,
              email: this.user.email,
              password: this.user.password,
              // role: this.user.role,
              roles: this.user.roles,
            })
            .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.user.id;
    },
  },
};
</script>

<style scoped></style>
