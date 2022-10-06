// JAVA program for implementation of KMP pattern
// searching algorithm
import java.nio.file.Path;
import java.nio.file.Files;

class KMP_String_Matching {
    void KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        int totalMatch = 0;
        String word = "";
        while ((N - i) >= (M - j)) {
            if (pat.charAt(j) == txt.charAt(i)) {
                word += txt.charAt(i);
                j++;
                i++;
            }
            if (j == M) {
                totalMatch++;
                System.out.println("Found pattern "
                        + "at index " + (i - j) + " | word match: " + word);
                j = lps[j - 1];
                word = "";
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
                word = "";
            }
        }
        System.out.println("Total number of match: "+totalMatch);
    }

    void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    // Driver program to test above function
    public static void main(String args[])
    {
        // try {
        //     File ponyText = new File('./pony_track.txt');
        //     Scanner myReader = new Scanner(ponyText);
        //     while (myReader.hasNextLine()) {
        //         System.out.print('hello');
        //     }   
        // } catch () {
        //     System.out.println('hello');
        // }
        String content = "";
        try {
            Path filePath = Path.of("./pony_track.txt");
            content = Files.readString(filePath);
        } catch (Exception e) {
           System.out.println("Error"); 
        }

        // String txt = "ABABDABACDABABCABAB";
        // String pat = "ABABCABAB";
        // String pat = "AB";
        System.out.println("Start regex..."); 
        String pat = "soldiers";
        new KMP_String_Matching().KMPSearch(pat, content);
        System.out.println("Regex end"); 
    }
}
// This code has been contributed by Amit Khandelwal.

