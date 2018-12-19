<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>J.A.R.V.I.S</title>
        <link rel="icon" type="image/png"  href="WEB-IMG/A_icon.png">
        <link rel="stylesheet" href="css/index-sub-content-galery.css"/>
    </head>

    <body>


        <!--Sub Content-->
        <div class="overlay-login-content" id="login-content">
            <a href="javascript:void(0)" class="closebtn-R" onclick="closeOverlayLogin()">&times;</a>
            <%@include  file="login.jsp" %>
        </div>

        <div class="overlay-gallery-content" id="overlay-gallery">
            <a href="javascript:void(0)" class="closebtn-L" onclick="closeOverlayGallery()">&times;</a>
            <div class="slideshow-container">

                <div class="mySlides fade">
                    <div class="numbertext">1 / 13</div>
                    <img src="image/1.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">2 / 13</div>
                    <img src="image/2.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">3 / 13</div>
                    <img src="image/3.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">4 / 13</div>
                    <img src="image/4.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">5 / 13</div>
                    <img src="image/5.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">6 / 13</div>
                    <img src="image/6.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">7 / 13</div>
                    <img src="image/7.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">8 / 13</div>
                    <img src="image/8.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">9 / 13</div>
                    <img src="image/9.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">10 / 13</div>
                    <img src="image/10.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">11 / 13</div>
                    <img src="image/11.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">12 / 13</div>
                    <img src="image/12.jpg" style="width:100%">
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">13 / 13</div>
                    <img src="image/13.jpg" style="width:100%">
                </div>

                <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                <a class="next" onclick="plusSlides(1)">&#10095;</a>

            </div>
            <br>


            <script>
                var slideIndex = 1;
                showSlides(slideIndex);

                function plusSlides(n) {
                    showSlides(slideIndex += n);
                }

                function currentSlide(n) {
                    showSlides(slideIndex = n);
                }

                function showSlides(n) {
                    var i;
                    var slides = document.getElementsByClassName("mySlides");
                    var dots = document.getElementsByClassName("dot");
                    if (n > slides.length) {
                        slideIndex = 1
                    }
                    if (n < 1) {
                        slideIndex = slides.length
                    }
                    for (i = 0; i < slides.length; i++) {
                        slides[i].style.display = "none";
                    }
                    for (i = 0; i < dots.length; i++) {
                        dots[i].className = dots[i].className.replace(" active", "");
                    }
                    slides[slideIndex - 1].style.display = "block";
                    dots[slideIndex - 1].className += " active";
                }
            </script>
        </div>

        <div class="overlay-mission-content" id="overlay-mission">
            <a href="javascript:void(0)" class="closebtn-L" onclick="closeOverlayMission()">&times;</a>
            <div class="mission-text" style="text-align: center; color: white; position: absolute; left: 150px; top: 200px; width: 1250px; height: 800px; font-size: 25px;"> 
                Iron Man, Thor, the Hulk and the rest of the Avengers unite to battle their most powerful enemy yet -- the evil Thanos. 
                On a mission to collect all six Infinity Stones, Thanos plans to use the artifacts to inflict his twisted will on reality. 
                The fate of the planet and existence itself has never been more uncertain as everything the Avengers have fought for has led up to this moment.
            </div>
        </div>

        <div class="overlay-about-content" id="overlay-about">
            <a href="javascript:void(0)" class="closebtn-R" onclick="closeAbout()">&times;</a>
            <div class="about-text" style="width: 800px; height: 500px; font-size: 30px; text-align: left; color: whitesmoke; position: absolute; left: 300px; top: 300px; "> 
                Student Name: Tran Dai Thanh<br/>
                Student ID: SE62847<br/>
                Class: SE1268<br/>
                Course: PRJ321<br/>
                Project Name: J.A.R.V.I.S<br/>
                Email: thanhtdse62847@fpt.edu.vn/daithanhtran2912@gmail.com<br/>
                Facebook: https://www.facebook.com/TZB291297
                
            </div>
        </div>
        <!--End of sub content -->