<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
        </v-toolbar>
        <v-form>
          <v-text-field v-model="newDescription" label="Description" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{"Add Details" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationDetails",
  props: {
    consultation: Object,
    opened: Boolean,
    newDescription: String,
  },
  methods: {
    onHidden(){
      this.opened = false;
    },
    persist() {
        api.consultations
            .addDetails({
              id: this.consultation.id,
              description: this.newDescription,
            })
            .then(() => this.$emit("refresh"));

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
