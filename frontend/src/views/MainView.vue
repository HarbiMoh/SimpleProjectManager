<template>
  <section class="dashboard">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div
            style="
              padding: 32px;
              box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
              background-color: #e7ffff;
            "
          >
            <h5 class="text-black-50 text-center">Welcome! Please sign in or sign up to proceed</h5>
            <br />
            <div class="text-center">
              <RouterLink to="/login" class="w-full"
                ><button class="btn btn-primary">Login</button></RouterLink
              >
              <br />
              <br />
              <RouterLink to="/signup" class="w-full"
                ><button class="btn btn-primary">Register</button></RouterLink
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
