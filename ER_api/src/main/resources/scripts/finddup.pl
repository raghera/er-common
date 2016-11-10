#!/usr/local/bin/perl
use diagnostics;
use strict;
use warnings;
use Getopt::Long;
 
my %dups = ();
my ($digest, $filename, $verbose) = ();
GetOptions("verbose" => \$verbose);
  
while (<>) {
      chomp;
      ($digest, $filename) = /(.{32})(.*)/;
      push(@{$dups{$digest}}, "\n");
      push(@{$dups{$digest}}, $filename);
}
  
foreach $digest (sort keys %dups) {
    if (scalar(@{$dups{$digest}}) > 2) {
        print "$digest" if ($verbose);
        print "@{$dups{$digest}}\n";
        print "\n" if ($verbose);
    }
}
