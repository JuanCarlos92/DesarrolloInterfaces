namespace GestorTareas
{
    partial class Form
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            TbTexto = new TextBox();
            LbLista = new ListBox();
            BtnAdd = new Button();
            BtnDelete = new Button();
            BtnSave = new Button();
            BtnLoad = new Button();
            SuspendLayout();
            // 
            // TbTexto
            // 
            TbTexto.Font = new Font("Segoe UI", 14.25F, FontStyle.Bold, GraphicsUnit.Point, 0);
            TbTexto.Location = new Point(21, 21);
            TbTexto.Multiline = true;
            TbTexto.Name = "TbTexto";
            TbTexto.Size = new Size(426, 47);
            TbTexto.TabIndex = 0;
            // 
            // LbLista
            // 
            LbLista.Font = new Font("Segoe UI", 14.25F, FontStyle.Regular, GraphicsUnit.Point, 0);
            LbLista.FormattingEnabled = true;
            LbLista.ItemHeight = 25;
            LbLista.Location = new Point(21, 84);
            LbLista.Name = "LbLista";
            LbLista.Size = new Size(426, 229);
            LbLista.TabIndex = 1;
            // 
            // BtnAdd
            // 
            BtnAdd.Location = new Point(21, 336);
            BtnAdd.Name = "BtnAdd";
            BtnAdd.Size = new Size(102, 44);
            BtnAdd.TabIndex = 2;
            BtnAdd.Text = "AÑADIR";
            BtnAdd.UseVisualStyleBackColor = true;
            BtnAdd.Click += BtnAdd_Click;
            // 
            // BtnDelete
            // 
            BtnDelete.Location = new Point(129, 336);
            BtnDelete.Name = "BtnDelete";
            BtnDelete.Size = new Size(102, 44);
            BtnDelete.TabIndex = 3;
            BtnDelete.Text = "ELIMINAR";
            BtnDelete.UseVisualStyleBackColor = true;
            BtnDelete.Click += BtnDelete_Click;
            // 
            // BtnSave
            // 
            BtnSave.Location = new Point(237, 336);
            BtnSave.Name = "BtnSave";
            BtnSave.Size = new Size(102, 44);
            BtnSave.TabIndex = 4;
            BtnSave.Text = "GUARDAR";
            BtnSave.UseVisualStyleBackColor = true;
            BtnSave.Click += BtnSave_Click;
            // 
            // BtnLoad
            // 
            BtnLoad.Location = new Point(345, 336);
            BtnLoad.Name = "BtnLoad";
            BtnLoad.Size = new Size(102, 44);
            BtnLoad.TabIndex = 5;
            BtnLoad.Text = "CARGAR";
            BtnLoad.UseVisualStyleBackColor = true;
            BtnLoad.Click += BtnLoad_Click;
            // 
            // Form
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(474, 404);
            Controls.Add(BtnLoad);
            Controls.Add(BtnSave);
            Controls.Add(BtnDelete);
            Controls.Add(BtnAdd);
            Controls.Add(LbLista);
            Controls.Add(TbTexto);
            Name = "Form";
            Text = "Gestor de Archivos";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox TbTexto;
        private ListBox LbLista;
        private Button BtnAdd;
        private Button BtnDelete;
        private Button BtnSave;
        private Button BtnLoad;
    }
}
