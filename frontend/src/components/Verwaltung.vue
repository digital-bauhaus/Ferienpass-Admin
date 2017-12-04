<template>
	<div>
    <h1>Verwaltung</h1>
    <form v-if="formDataLoaded" class="form">
      <h1>{{ formData.title }}</h1>
    </form>

    <p>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </p>
    <!-- v-if laden wenn formDataLoaded true, dann dürfte auch form-data.title
    vue.js doku guide-->

	</div>
</template>

<script>
export default {
  name: 'Verwaltung',
  data () {
    return {
      formDataLoaded: false,
      formData: null
    };
  },
  created () {
    this.fetchData();
  },
  methods: {
    fetchData () {
      fetch('/static/form-data.json')
        .then(response => {
          return response.json();
        })
        .then(json => {
          this.formData = json;
          this.formDataLoaded = true;
        });
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>

