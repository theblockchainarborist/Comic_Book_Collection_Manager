<template>
  <div>
      <div id="title">
         <h1 id="error-title">404</h1>
         <h2>Don't Hulk Out!</h2>
         <img id="hulk" src="../assets/hulk-smash.gif" alt="hulk smashing the ground"> 
         <h3>The page you're looking for doesn't exist, but we have many more to browse!</h3> 
      </div>
      
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>

#error-title {
    display: inline-block;
    width: 100vw;
    text-align: center;
    padding: 3%;
    font-size: 5rem;
}

#title {
    display: flex;
    flex-direction: column;
    align-items: center;
}

#hulk {
    padding: 1rem;
    width: 25%;
    height: auto;
}

</style>