namespace GestorTareas
{
    public partial class Form : System.Windows.Forms.Form
    {
        public Form()
        {
            InitializeComponent();
        }

        private void BtnAdd_Click(object sender, EventArgs e)
        {
            //si no esta vacio...
            if (!string.IsNullOrWhiteSpace(TbTexto.Text))
            {
                // Añade el texto a la lista
                LbLista.Items.Add(TbTexto.Text);

                TbTexto.Clear();
            }
            else
            {
                MessageBox.Show("Por favor, ingresa alguna tarea.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }

        private void BtnDelete_Click(object sender, EventArgs e)
        {
            // Si no esta vacio ...
            if (!string.IsNullOrWhiteSpace(TbTexto.Text))
            {
                // Entonces si existe...
                if (LbLista.Items.Contains(TbTexto.Text))
                {
                    // Elimina el texto de la lista
                    LbLista.Items.Remove(TbTexto.Text);

                    TbTexto.Clear();
                }
                else
                {
                    MessageBox.Show("La tarea no se encuentra en la lista.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            else
            {
                MessageBox.Show("Por favor, introduce la tarea que deseas eliminar.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }

        }

        private void BtnSave_Click(object sender, EventArgs e)
        {
            // Si en la lista hay + de 0 elementos
            if (LbLista.Items.Count > 0)
            {
                /*Crea un objeto de la clase SaveFileDialog y usamos los metodos:
                 * Filter: para filtar que sea un archivo de texto.txt
                 * Title: titulo
                 * FileName: Nombre del archivo*/

                SaveFileDialog saveFileDialog = new SaveFileDialog();
                saveFileDialog.Filter = "Archivo de texto (*.txt)|*.txt";
                saveFileDialog.Title = "Guardar lista de tareas";
                saveFileDialog.FileName = "listaTareas.txt";

                // Si es válida la ubicacion del usuario seleccionada...
                if (saveFileDialog.ShowDialog() == DialogResult.OK)
                {
                    // Con StreamWriter escribirmos en el archivo
                    using (System.IO.StreamWriter file = new System.IO.StreamWriter(saveFileDialog.FileName))
                    {
                        //bucle para recorrer linea x linea la lista y escribirlo en el archivo
                        foreach (var item in LbLista.Items)
                        {
                            file.WriteLine(item.ToString());
                        }
                    }
                    MessageBox.Show("Lista de tareas guardada correctamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
            else
            {
                MessageBox.Show("No hay tareas en la lista para guardar.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }

        private void BtnLoad_Click(object sender, EventArgs e)
        {
            /*Crea un objeto de la clase SaveFileDialog y usamos los metodos:
             * Filter: para filtar que sea un archivo de texto.txt
             * Title: titulo*/

            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Archivo de texto (*.txt)|*.txt";
            openFileDialog.Title = "Cargar lista de tareas";

            //Si se indica un archivo válido al pulsar abrir...
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {

                LbLista.Items.Clear();

                // Leer el archivo línea x línea y lo almacena en un array tipo string llamado linea
                string[] lines = System.IO.File.ReadAllLines(openFileDialog.FileName);

                // Bucle para añadir linea x linea a la lista
                foreach (string line in lines)
                {
                    LbLista.Items.Add(line);
                }

                MessageBox.Show("Lista de tareas cargada correctamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }
    }
}

