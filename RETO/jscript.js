window.addEventListener('scroll', function () {
  const scrollY = window.scrollY;
  const body = document.body;

  if (scrollY < 100) {
    body.style.backgroundColor = '#00b2beff'; // azul claro
  } else if (scrollY < 300) {
    body.style.backgroundColor = '#24acfc'; // color inicial
  } else if (scrollY < 600) {
    body.style.backgroundColor = '#1c93f3'; // azul claro
  } else if (scrollY < 900) {
    body.style.backgroundColor = '#1c3a56'; // verde suave
  } else if (scrollY < 1200) {
    body.style.backgroundColor = '#000c52ff'; // verde suave
  } else {
    body.style.backgroundColor = '#000000ff'; // tono cálido
  }
});
document.addEventListener("DOMContentLoaded", function () {
    const carousel = document.getElementById("eventCarousel");
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");

    let scrollAmount = 0;
    const scrollStep = 320; // ancho de tarjeta + margen
    const maxScroll = carousel.scrollWidth - carousel.clientWidth;

    // Función para avanzar manualmente
    function scrollNext() {
      scrollAmount = Math.min(scrollAmount + scrollStep, maxScroll);
      carousel.scrollTo({ left: scrollAmount, behavior: "smooth" });
    }

    // Función para retroceder manualmente
    function scrollPrev() {
      scrollAmount = Math.max(scrollAmount - scrollStep, 0);
      carousel.scrollTo({ left: scrollAmount, behavior: "smooth" });
    }

    // Eventos de los botones
    nextBtn.addEventListener("click", scrollNext);
    prevBtn.addEventListener("click", scrollPrev);

    // Reproducción automática cada 3 segundos
    setInterval(() => {
      if (scrollAmount >= maxScroll) {
        scrollAmount = 2; // reinicia al inicio
      } else {
        scrollAmount += scrollStep;
      }
      carousel.scrollTo({ left: scrollAmount, behavior: "smooth" });
    }, 2000);
  });
// Validación y manejo del formulario de registro
  (function () {
            'use strict';
            var form = document.getElementById('registroForm');
            var mensaje = document.getElementById('mensaje');

            form.addEventListener('submit', function (e) {
                if (!form.checkValidity()) {
                    e.preventDefault();
                    e.stopPropagation();
                } else {
                    e.preventDefault(); // quitar si se envía realmente a un servidor
                    mensaje.className = 'alert alert-success';
                    mensaje.textContent = 'Registro enviado correctamente. Gracias por registrarte.';
                    mensaje.style.display = 'block';
                    form.reset();
                    form.classList.remove('was-validated');
                    return;
                }
                form.classList.add('was-validated');
            }, false);
        })();