<!--
=========================================================
* Material Dashboard 2 - v3.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard
* Copyright 2021 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://www.creative-tim.com/license)
* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
-->
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="./assets/img/favicon.png">
        <title>
            Material Dashboard 2 by Creative Tim
        </title>
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
        <!-- Nucleo Icons -->
        <link href="./assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
        <!-- CSS Files -->
        <link id="pagestyle" href="./assets/css/material-dashboard.css?v=3.0.0" rel="stylesheet" />
    </head>

    <body class="">
        <div class="container position-sticky z-index-sticky top-0">
            <div class="row">
                <div class="col-12">
                    <!-- Navbar -->

                    <!-- End Navbar -->
                </div>
            </div>
        </div>
        <main class="main-content  mt-0">
            <section>
                <div class="page-header min-vh-100">
                    <div class="container">
                        <div class="row">
                            <div class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
                                <div class="position-relative bg-gradient-primary h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center" style="background-image: url('./assets/img/illustrations/illustration-signup.jpg'); background-size: cover;">
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-5 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5">
                                <div class="card card-plain">
                                    <div class="card-header">
                                        <h4 class="font-weight-bolder">    Enter your new password below.   </h4>
                                        <p class="mb-0">Enter your password new and confirm password to register</p>
                                    </div>
                                    <div class="card-body">   
            <form  class="form-ps" action="newPassword" method="POST">
                <br>
                <div class="new-password">
                    <h4 class="text-password">
                        New Password:
                    </h4>
                    <input type="text" name="password">
                    <div id="passwordError"></div>
                </div>
                <div class="confirm-password" >
                    <h4 class="text-password">
                        Confirm Password:
                    </h4>
                    <input type="password" name="newpassword">
                    <div id="repasswordError"></div>
                </div>
                <br>
                <div class="note-text">
                    Hint: To maker password is stronger, user
                    upper and lower case <br> letters, numbers, and
                    symbols like !"? $ % ^ &).
                </div>
                <div class="reset" style="text-align: center; margin-top: 10px;">
                  
                    <input type="submit" class="btn btn-secondary" value="Reset Password" id="submitButton">
                </div>
         
                <div style="text-align: center; margin-top: 10px;">
                    <button type="button" class="btn btn-secondary" onclick="goBack()">Come back</button>
                </div>
                <div class="notice-message">
                    <h1>${status}</h1>
                    <h1>${errorMessage}</h1>
                     <h1>${message}</h1>
                </div>
                   </form>
        </div>              



                                    </div>
                                    <div class="card-footer text-center pt-0 px-lg-2 px-1">
                                        <p class="mb-2 text-sm mx-auto">
                                            Already have an account?
                                            <a href="login" class="text-primary text-gradient font-weight-bold">Sign in</a>
                                        </p>
                                        <p class="text-sm text-center">
                                            Don't remember your password?
                                            <a href="forgotPassword" class="text-primary text-gradient font-weight-bold">Forgot Password</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

            </section>
        </main>
                   <script>
        function goBack() {
            window.history.back();
        }
    </script>
        <!--   Core JS Files   -->
        <script src="./assets/js/core/popper.min.js"></script>
        <script src="./assets/js/core/bootstrap.min.js"></script>
        <script src="./assets/js/plugins/perfect-scrollbar.min.js"></script>
        <script src="./assets/js/plugins/smooth-scrollbar.min.js"></script>
        <script>
            var win = navigator.platform.indexOf('Win') > -1;
            if (win && document.querySelector('#sidenav-scrollbar')) {
                var options = {
                    damping: '0.5'
                }
                Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
            }
        </script>
        <!-- Github buttons -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
        <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
        <script src="./assets/js/material-dashboard.min.js?v=3.0.0"></script>
    </body>

</html>
