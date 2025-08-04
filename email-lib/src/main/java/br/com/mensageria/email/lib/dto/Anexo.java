package br.com.mensageria.email.lib.dto;

/**
 * Objeto do anexo do email
 * 
 * - O tipoConteudo do anexo podem ser dos tipos mais variados e devem ser indicados a cada anexo enviado
	------DOCUMENTOS
	PDF				application/pdf
	Word (.docx)                    application/vnd.openxmlformats-officedocument.wordprocessingml.document
	Excel (.xlsx)                   application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
	Texto simples                   text/plain
	CSV				text/csv
	------IMAGENS
	JPG/JPEG                        image/jpeg
	PNG				image/png
	GIF				image/gif
	SVG	i			mage/svg+xml
	------ARQUIVOS COMPACTADOS
	ZIP				application/zip
	RAR				application/vnd.rar
	7Z				application/x-7z-compressed[
	------AUDIOS
	MP3				audio/mpeg
	MP4				video/mp4
	OGG				audio/ogg
 *
 * @author murilo.felipin
 */
public record Anexo(
    String nomeArquivo,
    String tipoConteudo, // Ex: application/pdf, image/png, etc
    String conteudoBase64
) {}
