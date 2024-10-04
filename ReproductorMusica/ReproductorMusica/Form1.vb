Imports System.Media
Imports System.Numerics

Public Class ReproductorMusica

    Private player As SoundPlayer = New SoundPlayer() 'Declaramos una variable SoundPlayer (archivos .wav)
    Private musica As String = "" 'Variable para almacenar la musica 

    Private Sub OpenFileDialog1_FileOk(sender As Object, e As System.ComponentModel.CancelEventArgs) Handles OpenFileDialog1.FileOk

    End Sub

    Private Sub BtPlay_Click(sender As Object, e As EventArgs) Handles BtPlay.Click
        If LbLista.SelectedItem IsNot Nothing Then
            musica = LbLista.SelectedItem.ToString()
            player.SoundLocation = musica
            player.Play()
            TbMusic.Text = "Reproduciendo: " & IO.Path.GetFileName(musica) 'Muestra la musica en el textBox
        Else
            MessageBox.Show("Por favor, selecciona un archivo de la lista.")
        End If
    End Sub

    Private Sub BtStop_Click(sender As Object, e As EventArgs) Handles BtStop.Click
        player.Stop()
        TbMusic.Text = "Detenido"
    End Sub

    Private Sub BtLoad_Click(sender As Object, e As EventArgs) Handles BtLoad.Click
        Dim openFileDialog As New OpenFileDialog()
        openFileDialog.Filter = "Archivos WAV (*.wav)|*.wav"
        openFileDialog.Multiselect = True

        If openFileDialog.ShowDialog() = DialogResult.OK Then
            For Each file In openFileDialog.FileNames
                LbLista.Items.Add(file) 'Añade a la lista la musica
            Next
        End If
    End Sub

    Private Sub LbLista_SelectedIndexChanged(sender As Object, e As EventArgs) Handles LbLista.SelectedIndexChanged

    End Sub

    Private Sub TbMusic_TextChanged(sender As Object, e As EventArgs) Handles TbMusic.TextChanged

    End Sub

    Private Sub ReproductorMusica_Load(sender As Object, e As EventArgs) Handles MyBase.Load

    End Sub
End Class

