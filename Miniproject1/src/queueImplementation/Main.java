package queueImplementation;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Playlist myPlaylist = new Playlist();
		Songs s1 = new Songs("badass");
		Songs s2 = new Songs("hunter vantaar");
		Songs s3 = new Songs("fear song");
		Songs s4 = new Songs("bella caio");
		Songs s5 = new Songs("Naa ready");
		Songs s6 = new Songs("once upon");
		Songs s7 = new Songs("jalebi baby");
		Songs s8 = new Songs("illuminati");
		Songs s9 = new Songs("google google");
		Songs s10 = new Songs("I'm pirate");
		Songs s11 = new Songs("bye bye bye");
		
		myPlaylist.playList.add(s1);
		myPlaylist.playList.add(s2);
		myPlaylist.playList.add(s3);
		myPlaylist.playList.add(s4);
		myPlaylist.playList.add(s5);
		myPlaylist.playList.add(s6);
		myPlaylist.playList.add(s7);
		myPlaylist.playList.add(s8);
		myPlaylist.playList.add(s9);
		myPlaylist.playList.add(s10);
		myPlaylist.playList.add(s11);
		
		main: while (true) {
			System.out.println("enter the function you want to do : \n1) add new song in playlist  \n2) veiw playlist \n3) add to queue \n4) view queue \n5) remove song in queue \n6) play songs \n7) exit");
			int opt = input.nextInt();
			switch (opt) {
			case 1:{
				input.nextLine();
				System.out.println("Enter the song name");
				String sname = input.nextLine();
				myPlaylist.addSongToPlaylist(new Songs(sname));
				break;
			}
			case 2: {
				myPlaylist.viewPlaylist();
				break;
			}
			case 3: {
				myPlaylist.viewPlaylist();
				System.out.println("enter song id to add in the queue : ");
				int selectedSong = input.nextInt();
				boolean check = true;
				for (int i=0; i<myPlaylist.playList.size(); i++) {
					 if (myPlaylist.playList.get(i).id == selectedSong) {
						 Playlist.queue.add(myPlaylist.playList.get(i));
						 check = false;
						 break;
					 }
				 }
				if (check) {
					System.out.println("the song is unavailable in the playlist..");
				}
				
				break;
			}
			case 4:{
				Playlist.viewQueue();
				break;
			}
			case 5:{
				Playlist.viewQueue();
				boolean check = true;
				System.out.println("enter song id to remove in the queue : ");
				int selectedSong = input.nextInt();
				for (int i=0; i<myPlaylist.playList.size(); i++) {
					 if (myPlaylist.playList.get(i).id == selectedSong) {
						 Playlist.queue.remove(myPlaylist.playList.get(i));
						 check = false;
						 break;
					 }
				 }
				if (check) {
					System.out.println("the song is unavailable in the queue..");
				}
				break;
			}
			case 6:{
				Playlist.playSong();
				break;
			}
			case 7:{
				break main;
			}
			default:{
				System.out.println("Invalid input");
			}
			}
		}
	}
}
