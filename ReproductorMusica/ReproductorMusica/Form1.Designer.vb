<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class ReproductorMusica
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        BtPlay = New Button()
        BtStop = New Button()
        OpenFileDialog1 = New OpenFileDialog()
        LbLista = New ListBox()
        TbMusic = New TextBox()
        BtLoad = New Button()
        SuspendLayout()
        ' 
        ' BtPlay
        ' 
        BtPlay.BackColor = Color.YellowGreen
        BtPlay.Font = New Font("Verdana", 12F, FontStyle.Italic, GraphicsUnit.Point, CByte(0))
        BtPlay.ForeColor = SystemColors.ControlText
        BtPlay.Location = New Point(100, 125)
        BtPlay.Name = "BtPlay"
        BtPlay.Size = New Size(113, 46)
        BtPlay.TabIndex = 0
        BtPlay.Text = "Reproducir"
        BtPlay.UseVisualStyleBackColor = False
        ' 
        ' BtStop
        ' 
        BtStop.BackColor = Color.Red
        BtStop.Font = New Font("Verdana", 12F, FontStyle.Italic, GraphicsUnit.Point, CByte(0))
        BtStop.Location = New Point(246, 124)
        BtStop.Name = "BtStop"
        BtStop.Size = New Size(113, 48)
        BtStop.TabIndex = 1
        BtStop.Text = "Parar"
        BtStop.UseVisualStyleBackColor = False
        ' 
        ' OpenFileDialog1
        ' 
        OpenFileDialog1.FileName = "OpenFileDialog1"
        ' 
        ' LbLista
        ' 
        LbLista.BackColor = Color.WhiteSmoke
        LbLista.Font = New Font("Segoe Print", 14.25F, FontStyle.Regular, GraphicsUnit.Point, CByte(0))
        LbLista.FormattingEnabled = True
        LbLista.ItemHeight = 33
        LbLista.Location = New Point(12, 195)
        LbLista.Name = "LbLista"
        LbLista.Size = New Size(594, 202)
        LbLista.TabIndex = 2
        ' 
        ' TbMusic
        ' 
        TbMusic.BackColor = Color.WhiteSmoke
        TbMusic.Font = New Font("Segoe Print", 14.25F, FontStyle.Bold, GraphicsUnit.Point, CByte(0))
        TbMusic.Location = New Point(98, 62)
        TbMusic.Multiline = True
        TbMusic.Name = "TbMusic"
        TbMusic.Size = New Size(406, 43)
        TbMusic.TabIndex = 3
        ' 
        ' BtLoad
        ' 
        BtLoad.BackColor = Color.MediumTurquoise
        BtLoad.Font = New Font("Verdana", 12F, FontStyle.Italic, GraphicsUnit.Point, CByte(0))
        BtLoad.Location = New Point(391, 124)
        BtLoad.Name = "BtLoad"
        BtLoad.Size = New Size(113, 48)
        BtLoad.TabIndex = 4
        BtLoad.Text = "Cargar"
        BtLoad.UseVisualStyleBackColor = False
        ' 
        ' ReproductorMusica
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        BackColor = SystemColors.ActiveCaption
        ClientSize = New Size(618, 432)
        Controls.Add(BtLoad)
        Controls.Add(TbMusic)
        Controls.Add(LbLista)
        Controls.Add(BtStop)
        Controls.Add(BtPlay)
        Name = "ReproductorMusica"
        Text = "Reproductor de Música"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents BtPlay As Button
    Friend WithEvents BtStop As Button
    Friend WithEvents OpenFileDialog1 As OpenFileDialog
    Friend WithEvents LbLista As ListBox
    Friend WithEvents TbMusic As TextBox
    Friend WithEvents BtLoad As Button

End Class
