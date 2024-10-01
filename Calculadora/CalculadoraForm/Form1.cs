using System.Linq.Expressions;

namespace CalculadoraForm
{
    public partial class calculadora : Form
    {
        private double valor1;
        private double valor2;
        private double resultado;
        private int operacion;

        public calculadora()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            //numero 0
            TbResultado.Text = TbResultado.Text + "0";
        }

        private void BtnIgual_Click(object sender, EventArgs e)
        {
            //igual
            valor2 = Convert.ToDouble(TbResultado.Text);

            switch (operacion)
            {
                case 1:
                    resultado = valor1 + valor2;
                    break;
                case 2:
                    resultado = valor1 - valor2;
                    break;
                case 3:
                    resultado = valor1 * valor2;
                    break;
                case 4:
                    resultado = valor1 / valor2;
                    break;
            }

            TbResultado.Text = resultado.ToString();    
        }

        private void BtnComa_Click(object sender, EventArgs e)
        {
            //punto
            TbResultado.Text = TbResultado.Text + ".";
        }

        private void BtnSuma_Click(object sender, EventArgs e)
        {
            //suma
            operacion = 1;
            valor1 = Convert.ToDouble(TbResultado.Text);
            TbResultado.Text = "";
        }

        private void BtnNum3_Click(object sender, EventArgs e)
        {
            //numero 3
            TbResultado.Text = TbResultado.Text + "3";
        }

        private void BtnNum2_Click(object sender, EventArgs e)
        {
            //numero 2
            TbResultado.Text = TbResultado.Text + "2";
        }

        private void BtnNum1_Click(object sender, EventArgs e)
        {
            //numero 1
            TbResultado.Text = TbResultado.Text + "1";
        }

        private void BtnRest_Click(object sender, EventArgs e)
        {
            //resta
            operacion = 2;
            valor1 = Convert.ToDouble(TbResultado.Text);
            TbResultado.Text = "";
        }

        private void BtnNum6_Click(object sender, EventArgs e)
        {
            //numero 6
            TbResultado.Text = TbResultado.Text + "6";
        }

        private void BtnNum5_Click(object sender, EventArgs e)
        {
            //numero 5
            TbResultado.Text = TbResultado.Text + "5";
        }

        private void BtnNum4_Click(object sender, EventArgs e)
        {
            //numero 4
            TbResultado.Text = TbResultado.Text + "4";
        }

        private void BtnMultip_Click(object sender, EventArgs e)
        {
            //multiplicar 
            operacion = 3;
            valor1 = Convert.ToDouble(TbResultado.Text);
            TbResultado.Text = "";
        } 

        private void BtnNum9_Click(object sender, EventArgs e)
        {
            //numero 9
            TbResultado.Text = TbResultado.Text + "9";
        }

        private void BtnNum8_Click(object sender, EventArgs e)
        {
            //numero 8
            TbResultado.Text = TbResultado.Text + "8";
        }

        private void BtnNum7_Click(object sender, EventArgs e)
        {
            //numero 7
            TbResultado.Text = TbResultado.Text + "7";
        }

        private void BtnDiv_Click(object sender, EventArgs e)
        {
            //Dividir
            operacion = 4;
            valor1 = Convert.ToDouble(TbResultado.Text);
            TbResultado.Text = "";
        }

        private void BtnBorrarTodo_Click(object sender, EventArgs e)
        {
            //Borrar
            TbResultado.Text = "";
        }

        private void BtnOff_Click(object sender, EventArgs e)
        {
            //salir
            Application.Exit();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            //TextBox Resultado
        }
    }
}
