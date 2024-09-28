package com.backendProyectoCorte2.ProyectoCorte2.exception;

public class Valoracion_estrellasException {
    
        private Integer Valoracion_Estrellas;
    
        public Integer getValoracion_Estrellas() {
            return Valoracion_Estrellas;
        }
    
        public void setValoracion_Estrellas(Integer valoracion_Estrellas) {
            if (esValoracionValida(valoracion_Estrellas)) {
                this.Valoracion_Estrellas = valoracion_Estrellas;
            } else {
                throw new IllegalArgumentException("La valoración debe estar entre 1 y 5.");
            }
        }
    
        // Método para validar la valoración
        private boolean esValoracionValida(Integer valoracion) {
            return valoracion != null && valoracion >= 1 && valoracion <= 5;
        }
    }
    

