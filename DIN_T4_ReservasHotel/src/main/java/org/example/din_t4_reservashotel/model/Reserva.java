package org.example.din_t4_reservashotel.model;

public class Reserva {
        private String dni;
        private String nombre;
        private String direccion;
        private String localidad;
        private String provincia;
        private String fechaLlegada;
        private String fechaSalida;
        private int numHabitacion;
        private String tipoHabitacion;
        private boolean esFumador;
        private String regimenAlojamiento;

        public Reserva(String dni, String nombre, String direccion, String localidad, String provincia, String fechaLlegada,
                       String fechaSalida, int numHabitacion, String tipoHabitacion, boolean esFumador, String regimenAlojamiento) {
            this.dni = dni;
            this.nombre = nombre;
            this.direccion = direccion;
            this.localidad = localidad;
            this.provincia = provincia;
            this.fechaLlegada = fechaLlegada;
            this.fechaSalida = fechaSalida;
            this.numHabitacion = numHabitacion;
            this.tipoHabitacion = tipoHabitacion;
            this.esFumador = esFumador;
            this.regimenAlojamiento = regimenAlojamiento;
        }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public boolean isEsFumador() {
        return esFumador;
    }

    public void setEsFumador(boolean esFumador) {
        this.esFumador = esFumador;
    }

    public String getRegimenAlojamiento() {
        return regimenAlojamiento;
    }

    public void setRegimenAlojamiento(String regimenAlojamiento) {
        this.regimenAlojamiento = regimenAlojamiento;
    }
}
